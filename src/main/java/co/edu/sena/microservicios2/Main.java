/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.microservicios2;

import co.edu.sena.controllers.TareaJpaController;
import co.edu.sena.entidades.Mascota;
import co.edu.sena.entidades.Tarea;
import co.edu.sena.microservicios2.utils.JsonTransformer;
import co.sena.vo.TareaVO;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import spark.Spark;

/**
 *
 * @author LEONARDO
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static ArrayList<Mascota> mascotas = new ArrayList<>();

    public static void main(String[] args) {
        TareaJpaController c = new TareaJpaController();
        //********
        Spark.port(8181);
        //Cargar template
        Main ma = new Main();
        Spark.get("/js", (req, res) -> ma.renderContent("control.js"));
        Spark.get("/", (req, res) -> ma.renderContent("index.html"));
        //Metodo get par obtener todos los datos de una entidad
        Spark.get("/mascotas", (request, response) -> {
            response.type("application/json");
            Hashtable<String, Object> retorno = new Hashtable<>();
            List<Tarea> tareas = c.findTareaEntities();
            List<TareaVO> tareasVo = new ArrayList<TareaVO>();
            if (tareas.size() > 0) {
                response.status(200);
                retorno.put("status", 200);
                retorno.put("message", "Datos encontrados, cantidad (" + mascotas.size() + ")");
            } else {
                response.status(201);
                retorno.put("status", 201);
                retorno.put("message", "No hay datos!");
            }
            for (Tarea tarea : tareas) {
                TareaVO tv = new TareaVO();
                tv.setId(tarea.getIdtarea());
                tv.setNombre(tarea.getDestarea());
                tv.setEstado(1);
                tareasVo.add(tv);
            }
            retorno.put("data",tareasVo);
            return retorno;
        }, new JsonTransformer()
        );

        //Metodo post para crear un nuevo objeto y agregar en la coleccion
        Spark.post("/mascotas", (request, response) -> {
            response.type("application/json");
            Hashtable<String, Object> retorno = new Hashtable<>();
            //Body que viene del cliente en JSON
            String body = request.body();
            //Conversion del  JSON a objeto por GSON
            Gson g = new Gson();
            try {
                Tarea t = g.fromJson(body, Tarea.class);
                c.create(t);
                //mascotas.add(m);
                response.status(200);
                retorno.put("status", 200);
                retorno.put("message", "Nuevo objeto guardado! ");

            } catch (JsonSyntaxException e) {
                response.status(400);
                retorno.put("status", 400);
                retorno.put("message", "Error de parseo: "+body+"<-" + e.getMessage());
            } catch (JsonParseException e) {
                response.status(400);
                retorno.put("status", 400);
                retorno.put("message", "Error de parseo: "+body+"<-" + e.getMessage());
            }

            return retorno;
        }, new JsonTransformer());

    }

    private String renderContent(String htmlFile) {
        String ruta = new File(".").getAbsolutePath();
        ruta = ruta.replace(".", "src\\main\\resources\\template\\") + htmlFile;
        try {
            return new String(Files.readAllBytes(Paths.get(ruta)), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
