package com.dk.test;
import net.sf.json.JSONObject;
import pjson.GetJsonData;

public class TestText2Json {

    public static void main(String[] args) {
        // 9090 9021
        String url="http://223.99.175.58:9090/dkcor/type1/_search?q=corIDTaskID:15090718473473561512021107357936";
//		String url="http://223.99.175.58:9098/dkcor/type1/_search?q=corIDTaskID:\"15090718473473561512181615305272\"&pretty=true&from=0&size=252";
        String txt=GetJsonData1.getUrlText(url);
//		String txt = GetJsonData.getTxtFileUniCode("cd58-2.txt");//cd58-2
        System.out.println("==============================sum:");
        String sum1=GetJsonData1.getDataSum(txt);
        System.out.println(sum1);
        System.out.println("==============================json:");
        String tagStr="商家名称:value,联系电话:value,联系地址:value,企业简介";
//		String tagStr="";
        JSONObject jA=GetJsonData1.getJsonData(txt,tagStr);
        System.out.println(jA);
        System.out.println("==============================txt:");
        String txt3=GetJsonData1.getGStringData(txt,tagStr);
        System.out.println(txt3);

    }

}