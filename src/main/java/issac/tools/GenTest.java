package issac.tools;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author issac.hu
 * @description todo
 * @date 2021/7/5 15:45
 */
public class GenTest extends CompareSqlUtils{

    public static void main(String[] args) throws Exception {
        projectLinkGen();
    }

    private static void projectLinkGen() throws SQLException, ClassNotFoundException, IOException {
        String[] concernTableNames = new String[]{"t_project", "t_project_link", "t_project_link_history",
                "t_project_link_share", "t_project_link_user_setting"};
        printCompareSql(concernTableNames);
    }


}
