package SmoothieArkisto;

import java.sql.*;
import spark.Spark;
import java.util.HashMap;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {
    
    public static void main(String[] args) throws Exception {
        
        Database database = new Database("jdbc:sqlite:tasks.db");
        
        RaakaAineDao raaka_aineet = new RaakaAineDao(database);
        AnnosDao annokset = new AnnosDao(database);

        Spark.get("/ainekset", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("raaka_aineet", raaka_aineet.findAll());

            return new ModelAndView(map, "ainekset");
        }, new ThymeleafTemplateEngine());

        Spark.post("/ainekset", (req, res) -> {
            RaakaAine aines = new RaakaAine(-1, req.queryParams("nimi"));
            raaka_aineet.saveOrUpdate(aines);

            res.redirect("/ainekset");
            return "";
        });
        
        Spark.get("/smoothiet", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("annokset", annokset.findAll());

            return new ModelAndView(map, "smoothiet");
        }, new ThymeleafTemplateEngine());

        Spark.post("/smoothiet", (req, res) -> {
            Annos annos = new Annos(-1, req.queryParams("nimi"));
            annokset.saveOrUpdate(annos);

            res.redirect("/smoothiet");
            return "";
        });

 
    }
    
}
