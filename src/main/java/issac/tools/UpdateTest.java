package issac.tools;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author issac.hu
 * @description
 * @date 2021/12/27 17:54
 */
public class UpdateTest extends CompareSqlUtils {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        update2();
    }

    private static void update1() throws ClassNotFoundException, SQLException {
        Connection srcConn = getConnection(srcUrl, srcUsername, srcPassword);
        Map<String, SqlBean> tableMap = getTableMap(srcConn, showTables(srcConn));
        for (Map.Entry<String, SqlBean> entry : tableMap.entrySet()) {
            String table = entry.getKey();
            SqlBean sqlBean = entry.getValue();
            Map<String, String> fieldMap = sqlBean.getFieldMap();
            if (fieldMap != null && !fieldMap.isEmpty()) {
                if (fieldMap.keySet().contains("`project_id`") && (fieldMap.keySet().contains("`create_by`") || fieldMap.keySet().contains("`user_id`"))) {
                    System.out.println("UPDATE " + table + " SET  create_by=xxx  WHERE project_id=yyy ;");
                }
            }
        }
        closeConnection(srcConn);
    }

    private static void update2() throws ClassNotFoundException, SQLException {
        Connection srcConn = getConnection(srcUrl, srcUsername, srcPassword);
        Map<String, SqlBean> tableMap = getTableMap(srcConn, showTables(srcConn));
        for (Map.Entry<String, SqlBean> entry : tableMap.entrySet()) {
            String table = entry.getKey();
            SqlBean sqlBean = entry.getValue();
            Map<String, String> fieldMap = sqlBean.getFieldMap();
            if (fieldMap != null && !fieldMap.isEmpty()) {
                if (fieldMap.keySet().contains("`create_by`")) {
                    System.out.println("UPDATE " + table + " SET  create_by='933556341971095552'  WHERE create_by='866048350602924032' ;");
                } else if (fieldMap.keySet().contains("`user_id`")) {
                    System.out.println("UPDATE " + table + " SET  user_id='933556341971095552'  WHERE user_id='866048350602924032' ;");
                }
            }
        }
        closeConnection(srcConn);
    }

    private static void update3() throws ClassNotFoundException, SQLException {
        Connection srcConn = getConnection(srcUrl, srcUsername, srcPassword);
        Map<String, SqlBean> tableMap = getTableMap(srcConn, showTables(srcConn));
        for (Map.Entry<String, SqlBean> entry : tableMap.entrySet()) {
            String table = entry.getKey();
            SqlBean sqlBean = entry.getValue();
            Map<String, String> fieldMap = sqlBean.getFieldMap();
            if (fieldMap != null && !fieldMap.isEmpty()) {
                if (fieldMap.keySet().contains("`project_id`")) {
                    System.out.println("UPDATE " + table + " SET  project_id=47670396764224  WHERE project_id=70835784037034 ;");
                }
            }
        }
        closeConnection(srcConn);
    }
}
