package com.dk.test

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by zzy on 15/12/24.
 */
class GetEsDataTest  {

    public static void main(String[] args) {
        testMatcher();
    }
  static  void testMatcher() {


        String  s = "String s = \"dsadsadas<peter>dsadasdas<lionel>\\\"www.163.com\\\"<kenny><>\";";

        Pattern p = Pattern.compile("(<[^>]*>)");
        Matcher m = p.matcher(s);
        List<String> result = new ArrayList<String>();
        while (m.find()) {
            result.add(m.group());
        }
        for (String s1:result) {
            System.out.println(s1);
        }

        String str = "q=corIDTaskID:\"15090718473473561512181615305272\"+AND+title1:北京&pretty=true"
//      String sdfs= "q=corIDTaskID:15090718473473561512181615305272+AND+title1:搜狐+AND+title2:搜狐&pretty=true";
      String sdfs = "q=corIDTaskID:"+"15090718473473561512181615305272"+"+"+"AND"+"+"+"title1:搜狐"+"+"+"AND"+"+"+"title2:搜狐"+"&pretty=true";

        String  cor = "^\\="
      Pattern  p1 = Pattern.compile("AND");
      Matcher m1 = p.matcher(sdfs);
      List<String> result1 = new ArrayList<String>();
      while (m1.find()) {
          result1.add(m.group());
      }
      for (String s1:result1) {
          System.out.println(s1);
      }
      System.out.println(sdfs);
    String and = "AND";
      System.out.println(and);
      String[] strs = sdfs.split(and);
      for(int i = 0; i < strs.length; i++) {

          if (strs[i].contains('+')){
//              strs[i] =strs[i].replace('','+');
              strs[i] = strs[i].replace('+','');
          System.out.printf("strs[%d] = %s%n", i, strs[i]);
          }



      }

      String [] corId =strs[0].split(":");
      System.out.println(corId[1]);



//      Pattern compile = Pattern.compile("q=corIDTaskID:([0-9]+)\\+AND\\+(.*)\\+AND\\+(.*)");

  }

    public static  void paramRex () {

        String params = "q=corIDTaskID:"+"15090718473473561512181615305272"+"+"+"AND"+"+"+"title1:搜狐"+"+"+"AND"+"+"+"title2:搜狐"+"&pretty=true";
        String and = "AND";
        System.out.println(and);
        String[] strs = params.split(and);
        for(int i = 0; i < strs.length; i++) {
            if (strs[i].contains('+')){
                strs[i] = strs[i].replace('+','');
                System.out.printf("strs[%d] = %s%n", i, strs[i]);
            }
        }

        String [] corId =strs[0].split(":");
        System.out.println(corId[1]);
        String

    }
}
