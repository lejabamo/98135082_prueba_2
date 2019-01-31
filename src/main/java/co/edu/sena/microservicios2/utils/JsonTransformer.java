/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.microservicios2.utils;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 *
 * @author LEONARDO
 */
public class JsonTransformer implements ResponseTransformer {
    private Gson gson= new Gson();
    
    @Override
    public String render(Object o) throws Exception{
        String json= gson.toJson(o);
        return json;
    }
}
