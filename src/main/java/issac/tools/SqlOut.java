package issac.tools;

import java.util.ArrayList;
import java.util.List;

/**
 * @author issac.hu
 * @description todo
 * @date 2021/6/25 16:50
 */
public class SqlOut {

    private List<String> addTables = new ArrayList<>();

    private List<String> addFields = new ArrayList<>();

    private List<String> modifyFields = new ArrayList<>();

    private List<String> addIndexes = new ArrayList<>();

    public List<String> getAddTables() {
        return addTables;
    }

    public void setAddTables(List<String> addTables) {
        this.addTables = addTables;
    }

    public List<String> getAddFields() {
        return addFields;
    }

    public void setAddFields(List<String> addFields) {
        this.addFields = addFields;
    }

    public List<String> getModifyFields() {
        return modifyFields;
    }

    public void setModifyFields(List<String> modifyFields) {
        this.modifyFields = modifyFields;
    }

    public List<String> getAddIndexes() {
        return addIndexes;
    }

    public void setAddIndexes(List<String> addIndexes) {
        this.addIndexes = addIndexes;
    }
}
