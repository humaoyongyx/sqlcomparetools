package issac.tools;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author issac.hu
 * @description todo
 * @date 2021/7/5 15:45
 */
public class GenTest extends CompareSqlUtils {

    static {
//        CompareSqlUtils.srcUrl = "jdbc:mysql://192.168.6.129:3306/bimcube?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=false&useTimezone=true&serverTimezone=Asia/Shanghai&useSSL=false";
//        CompareSqlUtils.srcUsername = "root";
//        CompareSqlUtils.srcPassword = "root";
//        CompareSqlUtils.targetUrl = "jdbc:mysql://192.168.6.129:3306/bimcube?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=false&useTimezone=true&serverTimezone=Asia/Shanghai&useSSL=false";
//        CompareSqlUtils.targetUsername = "root";
//        CompareSqlUtils.targetPassword = "root";
//        CompareSqlUtils.targetUrl = "jdbc:mysql://172.16.206.7:3306/bimcube?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=false&useTimezone=true&serverTimezone=Asia/Shanghai";
//        CompareSqlUtils.targetUsername = "root";
//        CompareSqlUtils.targetPassword = "Ma123";

    }

    public static void main(String[] args) throws Exception {
        projectLinkGen();
    }

    private static void projectLinkGen() throws SQLException, ClassNotFoundException, IOException {
      String[] concernTableNames = new String[]{"t_project", "t_project_link", "t_project_link_history","t_project_link_shape_rel","t_filter"};
      //  String[] concernTableNames = new String[]{"t_filter"};
        printCompareSql(concernTableNames);
    }


}
