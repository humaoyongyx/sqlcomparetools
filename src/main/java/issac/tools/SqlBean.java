package issac.tools;

import java.util.HashMap;
import java.util.Map;

/**
 * @author issac.hu
 * @description todo
 * @date 2021/6/25 15:06
 */
public class SqlBean {
    private String tableName;
    private String createSql;
    private Map<String, String> fieldMap = new HashMap<>();
    private Map<String, String> indexMap = new HashMap<>();

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCreateSql() {
        return createSql;
    }

    public void setCreateSql(String createSql) {
        this.createSql = createSql;
    }

    public Map<String, String> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, String> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public Map<String, String> getIndexMap() {
        return indexMap;
    }

    public void setIndexMap(Map<String, String> indexMap) {
        this.indexMap = indexMap;
    }
}
