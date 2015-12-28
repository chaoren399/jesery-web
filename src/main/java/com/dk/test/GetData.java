package com.dk.test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zzy on 15/12/22.
 */

//http://localhost:8080/search/corIDTaskID:15110820351316581512181627559962
//15090718473473561512181615305272 搜狐
//@Path("/search")
//@Path("/search/ty")
public class GetData {

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
//    @Path("/{param}")
    @Path("/{param}")
    @Produces("text/palin;charset=utf-8")
    public String getAllDataToUTF8(@PathParam("param") String parm) {
        if (!parm.toLowerCase().contains("coridtaskid:")) {

            return "url error";
        }
        String ip = "223.99.175.58:9021";
//        String url1 = "http://" +ip + "/index1/type1/_search?q=";
        String url1 = "http://" +ip + "/dkcor/type1/_search?q=";
//        parm = "15090718473473561512181615305272";
        String url = url1 + parm;
        url = Utf8URLencode(url);

        return  getESJson(url);
    }


    public static String Utf8URLencode (String text) {
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
}
