package com.dk.test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zzy on 15/12/23.
 */

//@Path("index1/type1/_search?q=")
//@Path("index11/type1/_search")
public class GetEsData {

    public String getAllData(){
        return "url error";
    }
    public String getESJson(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            URL url2 = new URL(url);

            HttpURLConnection connection = (HttpURLConnection)url2.openConnection();

            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine())!=null) {
                result += line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  result;


    }

    @GET
    @Path("/{param}")
    @Produces("text/palin;charset=utf-8")
//    @Consumes(value = {"text/xml","application/json"})
    public String getAllDataToUTF8(@PathParam("param") String parm,String sfa ) throws JSONException {
        if (!parm.toLowerCase().contains("coridtaskid:")) {

            return "url error";
        }
        String ip = "223.99.175.58:9098";
//        String url1 = "http://" +ip + "/index1/type1/_search?q=";
        String url1 = "http://" +ip + "/dkcor/type1/_search?q=";
//        parm = "15090718473473561512181615305272";
        String url = url1 + parm;
        url = Utf8URLencode(url);

//        return  getESJson(url);
        return  getJson(getESJson(url));
    }

    public String getJson(String string) throws JSONException {
        JSONObject dataJson = new JSONObject(string);
        JSONObject hits = dataJson.getJSONObject("hits");
//        JSONObject hitsJson = hits.getJSONObject("hits");

        JSONArray hitsArray = hits.getJSONArray("hits");
       JSONObject hitsobjet= (JSONObject) hitsArray.get(0);
//        hitsobjet.get("_type");
        JSONObject sourceObject = (JSONObject) hitsobjet.get("_source");
        sourceObject.get("title1");

        return   sourceObject.get("jsonData").toString();

//       return  searchTile(sourceObject.get("jsonData").toString());


    }

    public String searchTile (String string) throws JSONException {
        JSONObject object = new JSONObject(string);

        System.out.println( object.get("name"));
        return object.get("name").toString();
    }


    public  String Utf8URLencode (String text) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 0 && c <= 255) {
                result.append(c);
            } else {
                byte[] b = new byte[0];
                try {
                    b = Character.toString(c).getBytes("UTF-8");
                } catch (Exception ex) {
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0) k += 256;
                    result.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return result.toString();
    }

    public String getTxtFile(String filePath){
        try {
            File file = new File(filePath);
            FileReader reader = new FileReader(file);
            int fileLen = (int)file.length();
            char[] chars = new char[fileLen];
            reader.read(chars);
            String txt = String.valueOf(chars);
            reader.close();
            return txt;
        }catch(Exception e){
            //e.printStackTrace();
            return "";
        }
    }

    public void matcher (String str) {

    }


}
