package com.dk;

import com.dk.test.GetJsonData1;
//import org.json.JSONException;

//import javax.ws.rs.*;
import java.io.*;
import javax.ws.rs.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zzy on 15/12/23.
 */


@Path("/index1/type1/_search")
public class GetEsData1 {

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


    //    @Path("/{param}/{tagStr}")
//    @RequestMapping("/answer/{id}/{key}")
    @GET
    @Path("/{param}")
    @Produces("text/palin;charset=utf-8")
    @Consumes(value = {"text/xml","application/json"})
    public String getAllDataToUTF8(@PathParam("param") String parm )  {

        String urlerror = "";
        String tagerror1 = "tagstr1 error";
        String tagerror2 = "tagstr2 error";
        if (!parm.toLowerCase().contains("coridtaskid:")) {
            return "url error";
        }

        String ip = "223.99.175.58:9021";
        String url1 = "http://" +ip + "/dkcor/type1/_search?q=";
//        String url1 = "http://" +ip + "/dkcor/type1/";
//        parm = "15090718473473561512181615305272";
        String url = url1 + parm;
        url = Utf8URLencode(url);

//        return  getESJson(url);
        if (parm.toLowerCase().contains("tagstr1=")) {
           String [] tagstrs =  parm.split("&");
            System.out.println(tagstrs[1]);
            if (tagstrs[1] !=null){
                String [] tagValues = tagstrs[1].split("=");
              if (tagValues.length > 1){

                  String tagValue = tagValues[1];
                  String tagStr="商家名称:value,联系电话:value,联系地址:value,企业简介";
                  String url2 = url1 + tagstrs[0];
                    url2 = Utf8URLencode(url2);
                  String txt= GetJsonData1.getUrlText(url2);

                  net.sf.json.JSONObject jA=GetJsonData1.getJsonData(txt, tagValue);
                  System.out.println(jA);
                  return  jA.toString();
              }

            }
            return  tagerror1;
        }
        if (parm.toLowerCase().contains("tagstr2=") ) {
            String [] tagstrs =  parm.split("&");
            System.out.println(tagstrs[1]);
            if (tagstrs[1] !=null){
                String [] tagValues = tagstrs[1].split("=");
                if (tagValues.length > 1){

                    String tagValue = tagValues[1];
                    String tagStr="商家名称:value,联系电话:value,联系地址:value,企业简介";
                    String url2 = url1 + tagstrs[0];
                    url2 = Utf8URLencode(url2);
                    String txt=GetJsonData1.getUrlText(url2);


                    String txt3=GetJsonData1.getGStringData(txt,tagStr);


                    return  txt3;
                }

            }


            return  tagerror2;

        }



        //TODO
        // 1.获得某个任务编号下的数据总量
        // 2. 获取全部数据


        return getESJson(url);
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





}
