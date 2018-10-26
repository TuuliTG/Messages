
package tikape.minifoorumi;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Viestiketju")
public class Viestiketju {
    
    @DatabaseField (id = true, columnName = "id")
    private Integer id;
    @DatabaseField (columnName = "aihe")
    private String aihe;
    
    private int viesteja;

    public Viestiketju() {
    }

    public Viestiketju(Integer id, String aihe, int viesteja) {
        this.id = id;
        this.aihe = aihe;
        this.viesteja = viesteja;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    

    public String getAihe() {
        return aihe;
    }

    public int getViesteja() {
        return viesteja;
    }

    public void setAihe(String aihe) {
        this.aihe = aihe;
    }

    public void setViesteja(int viesteja) {
        this.viesteja = viesteja;
    }
    
    
}
