package tikape.minifoorumi;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class MinifoorumiApplication {

    public static void main(String[] args) throws Exception {
        
        String url = "/home/tgtuuli/NetBeansProjects/hy-tikape-s18/osa07-Osa07_03.Minifoorumi/db/forum.db";
        ConnectionSource connectionSource = new JdbcConnectionSource("jdbc:sqlite:" + url);
        
        Dao<Viesti, Integer> viestiDao = DaoManager.createDao(connectionSource, Viesti.class);
        Dao<Viestiketju, Integer> viestiketjuDao = DaoManager.createDao(connectionSource, Viestiketju.class);
        
        
        Spark.get("/etusivu", (req, res) -> {
            HashMap map = new HashMap();
            Viestiketju viestiketju = new Viestiketju();
            GenericRawResults<String[]> rawResults = viestiDao.queryRaw("SELECT teksti, MAX(lahetysaika) FROM Viesti");
            List<String[]> results = rawResults.getResults();
            String[] resultArray = results.get(0);
            String teksti = resultArray[0];
            
            viestiketju.setAihe(teksti);
            viestiketju.setId(1);
            viestiketjuDao.createOrUpdate(viestiketju);
            List<Viestiketju> viestiketjut = viestiketjuDao.queryForAll();
            map.put("viestiketjut", viestiketjut);
            
            return new ModelAndView(map, "etusivu");
        }, new ThymeleafTemplateEngine());
        
        Spark.get("/messages/:id", (req, res) -> {
            HashMap map = new HashMap();
            Integer viestiketjuId = Integer.parseInt(req.params("id"));
            QueryBuilder<Viesti, Integer> statementBuilder = viestiDao.queryBuilder();
            statementBuilder.where().eq(Viesti.VIESTIKETJU_ID_FIELD_NAME, viestiketjuId);
            List<Viesti> viestit = viestiDao.query(statementBuilder.prepare());
            map.put("viestit", viestit);
            
            
            return new ModelAndView(map, "messages");
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/messages", (req, res) -> {
            String teksti = req.queryParams("content");
            Viesti viesti = new Viesti();
            viesti.setTeksti(teksti);
            viestiDao.create(viesti);
            res.redirect("/messages");
                    
            return "";
            
        });
    }
}
