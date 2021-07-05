package issac.tools;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author issac.hu
 * @description todo
 * @date 2021/6/22 18:04
 */
public class CompareSqlUtils extends BaseConnectionUtils {

    private static String srcUrl = "jdbc:mysql://172.16.206.7:3306/bimcube?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=false&useTimezone=true&serverTimezone=Asia/Shanghai";
    private static String srcUsername = "root";
    private static String srcPassword = "Ma123";

//    private static String targetUrl = "jdbc:mysql://192.168.6.129:3306/bimcube?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=false&useTimezone=true&serverTimezone=Asia/Shanghai&useSSL=false";
//    private static String targetUsername = "root";
//    private static String targetPassword = "root";

    private static String targetUrl = "jdbc:mysql://172.16.206.7:3306/cube_data_r?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=false&useTimezone=true&serverTimezone=Asia/Shanghai";
    private static String targetUsername = "root";
    private static String targetPassword = "Ma123";

    private static File file = new File("D:\\mycode\\tools\\src\\main\\java\\issac\\tools\\out.sql");



    public static void printCreateSql() throws SQLException, IOException, ClassNotFoundException {
        String[] concernTableNames = new String[]{"t_duct_category", "t_shape_duct", "t_duct_type",
                "t_duct_type_props", "t_sys_category", "t_duct_specs", "t_duct_sys", "t_duct_sys_props"};
        printCreateSql(concernTableNames);
    }

    private static List<String> getConcernTableList(String[] tables) {
        return Arrays.stream(tables).map(it -> getTableName(it)).distinct().collect(Collectors.toList());
    }

    public static void printCompareSql(String[] tables) throws SQLException, ClassNotFoundException, IOException {
        SqlOut sqlOut = compareSql(tables);
        PrintWriter printWriter = new PrintWriter(file);
        for (String addTable : sqlOut.getAddTables()) {
            System.out.println(addTable);
            printWriter.println(addTable);
        }
        for (String addField : sqlOut.getAddFields()) {
            System.out.println(addField);
            printWriter.println(addField);
        }
        for (String modifyField : sqlOut.getModifyFields()) {
            System.out.println(modifyField);
            printWriter.println(modifyField);
        }
        for (String addIndex : sqlOut.getAddIndexes()) {
            System.out.println(addIndex);
            printWriter.println(addIndex);
        }
        closePrintWriter(printWriter);
    }

    private static boolean checkConcernTables(String[] tables) {
        if (tables != null || tables.length > 0) {
            return true;
        }
        return false;
    }

    private static SqlOut compareSql(String[] tables) throws SQLException, ClassNotFoundException {
        //源表
        Connection srcConn = getConnection(srcUrl, srcUsername, srcPassword);
        List<String> srcTables = checkConcernTables(tables) ? getConcernTableList(tables) : showTables(srcConn);
        Map<String, SqlBean> srcTableMap = getTableMap(srcConn, srcTables);
        //目标表
        Connection targetConn = getConnection(targetUrl, targetUsername, targetPassword);
        List<String> targetTables = showTables(targetConn);
        Map<String, SqlBean> targetTableMap = getTableMap(targetConn, targetTables);
        SqlOut sqlOut = new SqlOut();
        List<String> addTables = new ArrayList<>();
        List<String> addFields = new ArrayList<>();
        List<String> modifyFields = new ArrayList<>();
        List<String> addIndexes = new ArrayList<>();
        for (SqlBean srcSqlBean : srcTableMap.values()) {
            SqlBean targetSqlBean = targetTableMap.get(srcSqlBean.getTableName());
            if (targetSqlBean == null) {
                //目标db没有源db的表
                addTables.add(srcSqlBean.getCreateSql());
            } else {
                //处理字段
                handleFields(addFields, modifyFields, srcSqlBean, targetSqlBean);
                handleIndexes(addIndexes, srcSqlBean, targetSqlBean);
            }
        }
        sqlOut.setAddTables(addTables);
        sqlOut.setAddFields(addFields);
        sqlOut.setModifyFields(modifyFields);
        sqlOut.setAddIndexes(addIndexes);
        closeConnection(srcConn);
        closeConnection(targetConn);
        return sqlOut;
    }

    private static void handleIndexes(List<String> addIndexes, SqlBean srcSqlBean, SqlBean targetSqlBean) {
        Map<String, String> srcIndexMap = srcSqlBean.getIndexMap();
        Map<String, String> targetIndexMap = targetSqlBean.getIndexMap();
        for (Map.Entry<String, String> entry : srcIndexMap.entrySet()) {
            String srcIndexSql = entry.getKey();
            if (targetIndexMap.get(srcIndexSql) == null) {
                //说明目标表不存在，源索引
                String addIndexSql = "ALTER TABLE " + srcSqlBean.getTableName() + " ADD " + srcIndexSql + ";";
                addIndexes.add(addIndexSql);
            }
            //这里只有存在，也即相等这种情况，所以没有吧else
        }
    }

    private static void handleFields(List<String> addFields, List<String> modifyFields, SqlBean srcSqlBean, SqlBean targetSqlBean) {
        Map<String, String> targetFieldMap = targetSqlBean.getFieldMap();
        Map<String, String> srcFieldMap = srcSqlBean.getFieldMap();
        for (Map.Entry<String, String> srcEntry : srcFieldMap.entrySet()) {
            String srcField = srcEntry.getKey();
            String srcFieldRawSql = srcEntry.getValue();
            String targetFieldRawSql = targetFieldMap.get(srcField);
            if (targetFieldRawSql == null) {
                //目标db没有源db的字段
                String addFieldSql = "ALTER TABLE " + srcSqlBean.getTableName() + " ADD COLUMN " + srcFieldRawSql + ";";
                addFields.add(addFieldSql);
            } else {
                //如果存在，判断是否相等
                if (!targetFieldRawSql.equals(srcFieldRawSql)) {
                    //不相等的话，修改
                    String modifyFieldSql = "ALTER TABLE " + srcSqlBean.getTableName() + " MODIFY COLUMN " + srcFieldRawSql + ";";
                    modifyFields.add(modifyFieldSql);
                }
            }
        }
    }

    public static void printCreateSql(String[] concernTableNames) throws ClassNotFoundException, SQLException, IOException {
        PrintWriter printWriter = new PrintWriter(file);
        Connection connection = getConnection(srcUrl, srcUsername, srcPassword);
        List<String> tables;
        if (concernTableNames == null || concernTableNames.length == 0) {
            tables = showTables(connection);
        } else {
            tables = Arrays.stream(concernTableNames).map(it -> getTableName(it)).collect(Collectors.toList());
        }
        Map<String, SqlBean> tableMap = getTableMap(connection, tables);
        for (String concernTableName : concernTableNames) {
            SqlBean sqlBean = tableMap.get(getTableName(concernTableName));
            System.out.println(sqlBean.getCreateSql());
            printWriter.println("");
            printWriter.println(sqlBean.getCreateSql());
        }
        closeConnection(connection);
        closePrintWriter(printWriter);
    }

    public static Map<String, SqlBean> getTableMap(Connection connection, List<String> tableNames) throws SQLException {
        Map<String, SqlBean> result = new HashMap<>();
        if (tableNames == null) {
            return result;
        }
        SqlBean sqlBean;
        for (String tableName : tableNames) {
            sqlBean = new SqlBean();
            sqlBean.setTableName(tableName);
            String rawSql = showCreateTableSql(connection, tableName);
            String createSql = extractCreateSql(rawSql);
            sqlBean.setCreateSql(createSql);
            Map<String, Map<String, String>> extractLineMap = extractLineMap(createSql);
            Map<String, String> fieldMap = extractLineMap.get("field");
            sqlBean.setFieldMap(fieldMap);
            Map<String, String> indexMap = extractLineMap.get("index");
            sqlBean.setIndexMap(indexMap);
            result.put(tableName, sqlBean);
        }
        return result;
    }

    private static Map<String, Map<String, String>> extractLineMap(String createSql) {
        Map<String, Map<String, String>> result = new HashMap<>();
        Map<String, String> fieldMap = new HashMap<>();
        Map<String, String> indexMap = new HashMap<>();
        String[] split = createSql.split("\n");
        for (int i = 1; i < split.length - 1; i++) {
            String line = split[i].replace(",", "").trim();
            if (line.startsWith("`")) {
                String fieldName = line.split("\\s")[0];
                fieldMap.put(fieldName, line);
            } else {
                indexMap.put(line, line);
            }
        }
        result.put("field", fieldMap);
        result.put("index", indexMap);
        return result;
    }

    private static String extractCreateSql(String rawSql) {
        List<String> replaceList = getReplaceList();
        for (String str : replaceList) {
            rawSql = rawSql.replace(str, "");
        }
        rawSql = rawSql.replaceAll("[^\\S\\r\\n]+", " ") + ";";
        return rawSql;
    }

    public static List<String> getReplaceList() {
        List<String> result = new ArrayList<>();
        result.add("CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci");
        result.add("CHARACTER SET utf8mb4 COLLATE utf8mb4_bin");
        result.add("ENGINE=InnoDB");
        result.add("DEFAULT CHARSET=utf8mb4");
        result.add("COLLATE=utf8mb4_0900_ai_ci");
        result.add("ROW_FORMAT=DYNAMIC");
        result.add("USING BTREE");
        return result;
    }

    public static List<String> showTables(Connection connection) throws SQLException {
        List<String> result = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("show tables");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String tableName = resultSet.getString(1);
            result.add(getTableName(tableName));
        }
        closeRsAndPst(resultSet, preparedStatement);
        return result;
    }

    public static String getTableName(String tableName) {
        return "`" + tableName + "`";
    }

    public static String showCreateTableSql(Connection connection, String tableName) throws SQLException {
        String sql = "show create table " + tableName;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            return resultSet.getString(2);
        }
        return null;
    }

}
