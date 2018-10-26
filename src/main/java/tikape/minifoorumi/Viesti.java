
package tikape.minifoorumi;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "Viesti")
public class Viesti {
    
    public static final String VIESTIKETJU_ID_FIELD_NAME = "viestiketju_id";
    @DatabaseField(id = true, columnName = "id")
    private Integer id;
    @DatabaseField(foreign = true, columnName = VIESTIKETJU_ID_FIELD_NAME)
    private Viestiketju viestiketju;
    @DatabaseField(columnName = "teksti")
    private String teksti;
    @DatabaseField
    private String lahetysaika;

    public Viesti() {
    }

    public Viesti(Integer id, Viestiketju viestiketju, String teksti, String lahetysaika) {
        this.id = id;
        this.viestiketju = viestiketju;
        this.teksti = teksti;
        this.lahetysaika = lahetysaika;
    }

    

    public Integer getId() {
        return id;
    }
    

    public String getTeksti() {
        return teksti;
    }

    public void setTeksti(String teksti) {
        this.teksti = teksti;
    }

    public String getLahetysaika() {
        return lahetysaika;
    }

    public Viestiketju getViestiketju() {
        return viestiketju;
    }
    
    
    
}
