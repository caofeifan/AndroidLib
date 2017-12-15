package com.example.androidlib.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/**
 * Created by cao on 2017/12/14.
 * 该Gson工具可以将实例对象、list、map等转换为json，
 * 同时也可以将json转换为对象、list、map等。
 */
public class GsonUtil {
    private static Gson gson = null;
    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    private GsonUtil() {
    }

    /**
     * 转成json
     *
     * @param object
     * @return
     */
    public static String GsonString(Object object) {
        String gsonString = null;
        try {
            if (gson != null) {
                gsonString = gson.toJson(object);
            }
        }catch (Exception e){
            System.out.println("Json 为空");
        }finally {
            return gsonString;
        }


    }

    /**
     * 转成bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T GsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        try{
            if (gson != null) {
                t = gson.fromJson(gsonString, cls);
            }
        }catch (NullPointerException e){
            System.out.println("Json 为 null，或者为\"\"");
        }catch (JsonSyntaxException e){
            System.out.println("Json 格式出错");
        }finally {
            return t;
        }
    }

    /**
     * 转成list
     * 泛型在编译期类型被擦除导致报错
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> List<T> GsonToList(String gsonString, Class<T> cls) {
        List<T> list = null;
        try{
            if (gson != null) {
                list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
                }.getType());
            }
        }catch (JsonSyntaxException e){
            System.out.println("json 格式出错");
        }finally {
            return list;
        }
    }

    /**
     * 转成list
     * 解决泛型问题
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public <T> List<T> jsonToList(String json, Class<T> cls) {
        Gson gson = new Gson();
        List<T> list = new ArrayList<T>();
        try{
            JsonArray array = new JsonParser().parse(json).getAsJsonArray();
            for(final JsonElement elem : array){
                list.add(gson.fromJson(elem, cls));
            }
        }catch (JsonSyntaxException e){
            System.out.println("json 格式出错");
        }finally {
            return list;
        }
    }


    /**
     * 转成list中有map的list
     *
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        try{
            if (gson != null) {
                list = gson.fromJson(gsonString,
                        new TypeToken<List<Map<String, T>>>() {
                        }.getType());
            }
        }catch (JsonSyntaxException e){
            System.out.println("json 格式出错");
        }finally {
            return list;
        }
    }

    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> GsonToMaps(String gsonString) {
        Map<String, T> map = null;
        try {
            if (gson != null) {
                map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
                }.getType());
            }
        }catch (JsonSyntaxException e){
            System.out.println("json 格式出错");
        }finally {
            return map;
        }
    }
}
