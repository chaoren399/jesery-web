package com.dk.test;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetJsonData1 {
    public GetJsonData1() {
    }

    public static String getTxtFile(String filePath) {
        try {
            File e = new File(filePath);
            FileReader reader = new FileReader(e);
            int fileLen = (int)e.length();
            char[] chars = new char[fileLen];
            reader.read(chars);
            String txt = String.valueOf(chars);
            reader.close();
            return txt;
        } catch (Exception var6) {
            return "";
        }
    }

    public static String getTxtFileUniCode(String filePath) {
        try {
            String e = "Unicode";
            InputStreamReader read = new InputStreamReader(new FileInputStream(filePath), e);
            BufferedReader bufread = new BufferedReader(read);

            String tmpLineVal;
            String txt;
            for(txt = ""; (tmpLineVal = bufread.readLine()) != null; txt = txt + tmpLineVal) {
                ;
            }

            bufread.close();
            read.close();
            return txt;
        } catch (Exception var7) {
            return "";
        }
    }

    public static String getUrlText(String url) {
        String result = "";
        BufferedReader in = null;

        try {
            URL url2 = new URL(url);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
            System.setProperty("sun.net.client.defaultReadTimeout", "30000");
            URLConnection connection = url2.openConnection();
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";

            while((line = in.readLine()) != null) {
                buffer.append(line);
            }

            String var8 = buffer.toString();
            return var8;
        } catch (Exception var16) {
            ;
        } finally {
            try {
                if(in != null) {
                    in.close();
                }
            } catch (Exception var15) {
                ;
            }

        }

        return result;
    }

    private static JSONObject getVArJson(String tagStr, JSONArray valueAr) {
        JSONObject ja1 = new JSONObject();
        if(tagStr == null | tagStr.length() < 1) {
            return ja1;
        } else {
            String[] tagAr = tagStr.split(",");
            if(tagAr.length < 1) {
                return ja1;
            } else if(valueAr.size() < 1) {
                return ja1;
            } else {
                HashMap map = new HashMap();
                map.clear();

                for(int jj = 0; jj < valueAr.size(); ++jj) {
                    for(int j = 0; j < tagAr.length; ++j) {
                        String[] tagAr2 = tagAr[j].split(":");
                        String s1;
                        String s10;
                        String s2;
                        String s11;
                        if(tagAr2.length == 1) {
                            s1 = tagAr2[0];
                            s10 = valueAr.getJSONObject(jj).get("name").toString();
                            s2 = valueAr.getJSONObject(jj).get("value").toString();
                            if(s1 != null && s1.length() > 0 && s1.equals(s10)) {
                                s11 = "";
                                String[] s22 = s2.split("c0cb5f0fcf239ab3d9c1fcd31fff1efc");
                                if(s22.length > 1) {
                                    s11 = s22[1];
                                    map.put(s10, s11);
                                }
                            }
                        }

                        if(tagAr2.length == 2) {
                            s1 = tagAr2[0] + "_attr";
                            s10 = tagAr2[0];
                            s2 = tagAr2[1];
                            s11 = valueAr.getJSONObject(jj).get("name").toString();
                            String var17 = valueAr.getJSONObject(jj).get("value").toString();
                            if(s1 != null && s1.length() > 0 && s2 != null && s2.length() > 0 && s1.equals(s11)) {
                                String s33 = "";
                                String[] aN = var17.split("c0cb5f0fcf239ab3d9c1fcd31fff1efc");
                                if(aN.length > 0) {
                                    for(int k = 0; k < aN.length; ++k) {
                                        s33 = aN[k];
                                        String[] s33Ar = s33.split("=");
                                        if(s33Ar.length == 2 && s33.contains(s2)) {
                                            s33 = s33Ar[1];
                                            map.put(s10, s33);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                ja1 = JSONObject.fromObject(map);
                return ja1;
            }
        }
    }

    public static JSONObject getJsonData(String txt, String tagStr) {
        JSONArray jAr = new JSONArray();
        JSONObject jso = new JSONObject();
        if(txt == null | txt.length() < 1) {
            return jso;
        } else if(tagStr == null | tagStr.length() < 1) {
            return jso;
        } else {
            String[] tagAr = tagStr.split(",");
            if(tagAr.length < 1) {
                return jso;
            } else {
                JSONObject jodata = JSONObject.fromObject(txt);
                if(jodata.get("hits") == null) {
                    return jso;
                } else {
                    JSONObject jHits = JSONObject.fromObject(jodata.get("hits").toString());
                    if(jHits.get("hits") == null) {
                        return jso;
                    } else {
                        JSONArray jHitsAr = JSONArray.fromObject(jHits.get("hits"));

                        try {
                            for(int i = 0; i < jHitsAr.size(); ++i) {
                                JSONObject o = jHitsAr.getJSONObject(i);
                                if(o.get("_source") != null) {
                                    JSONObject o2 = o.getJSONObject("_source");
                                    if(o2.get("jsonData") != null) {
                                        String jsonData = o2.get("jsonData").toString();
                                        JSONObject o3 = JSONObject.fromObject(jsonData);
                                        String url = o3.get("name").toString();
                                        JSONArray valueAr = o3.getJSONArray("value");
                                        JSONObject ja1 = getVArJson(tagStr, valueAr);
                                        ja1.accumulate("url", url);
                                        if(ja1 != null) {
                                            jAr.add(ja1);
                                        }
                                    }
                                }
                            }
                        } catch (Exception var16) {
                            ;
                        }

                        jso.accumulate("result", jAr);
                        return jso;
                    }
                }
            }
        }
    }

    public static String getVArStr(String tagStr, JSONArray valueAr) {
        if(valueAr.size() < 1) {
            return "";
        } else {
            String[] tagAr = tagStr.split(",");
            if(tagAr.length < 1) {
                return "";
            } else {
                HashMap map = new HashMap();
                map.clear();
                String str = "";

                for(int jj = 0; jj < valueAr.size(); ++jj) {
                    for(int j = 0; j < tagAr.length; ++j) {
                        String[] tagAr2 = tagAr[j].split(":");
                        String s1;
                        String s2;
                        String s11;
                        String s22;
                        if(tagAr2.length == 1) {
                            s1 = tagAr2[0];
                            s2 = valueAr.getJSONObject(jj).get("name").toString();
                            s11 = valueAr.getJSONObject(jj).get("value").toString();
                            if(s1 != null && s1.length() > 0 && s1.equals(s2)) {
                                s22 = "";
                                String[] s33 = s11.split("c0cb5f0fcf239ab3d9c1fcd31fff1efc");
                                if(s33.length > 1) {
                                    s22 = s33[1];
                                    str = str + s22 + ",";
                                }
                            }
                        }

                        if(tagAr2.length == 2) {
                            s1 = tagAr2[0] + "_attr";
                            s2 = tagAr2[1];
                            s11 = valueAr.getJSONObject(jj).get("name").toString();
                            s22 = valueAr.getJSONObject(jj).get("value").toString();
                            if(s1 != null && s1.length() > 0 && s2 != null && s2.length() > 0 && s1.equals(s11)) {
                                String var16 = "";
                                String[] aN = s22.split("c0cb5f0fcf239ab3d9c1fcd31fff1efc");
                                if(aN.length > 0) {
                                    for(int k = 0; k < aN.length; ++k) {
                                        var16 = aN[k];
                                        String[] s33Ar = var16.split("=");
                                        if(s33Ar.length == 2 && var16.contains(s2)) {
                                            var16 = s33Ar[1];
                                            str = str + var16 + ",";
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if(str.length() > 1) {
                    str = str.substring(0, str.length() - 1);
                }

                return str;
            }
        }
    }

    public static String getGStringData(String txt, String tagStr) {
        String str = "";
        String str2 = "";
        if(txt == null | txt.length() < 1) {
            return str;
        } else if(tagStr == null | tagStr.length() < 1) {
            return str;
        } else {
            JSONObject jodata = JSONObject.fromObject(txt);
            if(jodata.get("hits") == null) {
                return str;
            } else {
                JSONObject jHits = JSONObject.fromObject(jodata.get("hits").toString());
                if(jHits.get("hits") == null) {
                    return str;
                } else {
                    JSONArray jHitsAr = jHits.getJSONArray("hits");

                    try {
                        for(int i = 0; i < jHitsAr.size(); ++i) {
                            JSONObject o = jHitsAr.getJSONObject(i);
                            JSONObject o2 = o.getJSONObject("_source");
                            if(o2.get("jsonData") != null) {
                                String jsonData = o2.get("jsonData").toString();
                                JSONObject o3 = JSONObject.fromObject(jsonData);
                                String url = o3.get("name").toString();
                                JSONArray valueAr = o3.getJSONArray("value");
                                str = getVArStr(tagStr, valueAr) + "," + url;
                                str2 = str2 + str + "\r\n";
                            }
                        }
                    } catch (Exception var14) {
                        ;
                    }

                    return str2;
                }
            }
        }
    }

    public static String getDataSum(String txt) {
        String str = "0";

        try {
            if(txt == null | txt.length() < 1) {
                return str;
            }

            JSONObject jodata = JSONObject.fromObject(txt);
            if(jodata.get("hits") == null) {
                return str;
            }

            JSONObject jHits = JSONObject.fromObject(jodata.get("hits").toString());
            if(jHits.get("total") == null) {
                return str;
            }

            str = jHits.get("total").toString();
        } catch (Exception var4) {
            ;
        }

        return str;
    }

    public static void main(String[] args) {
        System.out.println("getjsondata");
    }
}
