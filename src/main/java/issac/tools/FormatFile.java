package issac.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author issac.hu
 * @description
 * @date 2021/9/22 17:24
 */
public class FormatFile extends CompareSqlUtils {
    public static File rawSqlFile = new File("D:\\workcode\\sqlcomparetools\\src\\main\\java\\issac\\tools\\raw.sql");
    public static File outFile = new File("D:\\workcode\\sqlcomparetools\\src\\main\\java\\issac\\tools\\out.sql");

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(rawSqlFile);
        scanner.useDelimiter("\n");

        StringBuffer stringBuffer = new StringBuffer();
        List<String> sqlList = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.next();
            if (line.contains("CREATE TABLE")) {
                stringBuffer = new StringBuffer();
            }
            stringBuffer.append(line);
            if (line.contains(";")) {
                sqlList.add(stringBuffer.toString());
            }
        }
        if (!sqlList.isEmpty()) {
            PrintWriter printWriter = new PrintWriter(outFile);
            for (String sql : sqlList) {
                String createSql = extractCreateSql(sql);
                printWriter.println(createSql);
            }
            printWriter.flush();
        }
    }

    public static String extractCreateSql(String rawSql) {
        List<String> replaceList = getReplaceList();
        for (String str : replaceList) {
            rawSql = rawSql.replace(str, "");
        }
        rawSql = rawSql.replace("\r", "\r\n");
        return rawSql;
    }
}
