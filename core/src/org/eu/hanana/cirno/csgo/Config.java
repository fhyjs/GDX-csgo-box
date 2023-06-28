package org.eu.hanana.cirno.csgo;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Logger;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.eu.hanana.cirno.csgo.res.FileManager;
import org.eu.hanana.cirno.csgo.util.Trace;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.Objects;

public class Config {
    private static final Logger logger = new Logger("Config", Logger.DEBUG);
    public static String Language;
    public static void Sync() throws URISyntaxException {
        File file = new File(FileManager.getJarPath() + "config.json");
        if (!file.exists()) {
            setDefault();
            writeFile(file);
        }
        readFile(file);
    }
    public static void write() throws URISyntaxException {
        File file = new File(FileManager.getJarPath() + "config.json");
        writeFile(file);
    }
    public static void readFile(File file){
        Class<Config> clazz = Config.class;
        Field[] fields = clazz.getDeclaredFields();
        Gson json = new Gson();
        for (Field field : fields) {
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                try {
                    field.setAccessible(true);
                    String name = field.getName();
                    String type = field.getType().getSimpleName();
                    if (name.equals("logger")) continue;

                    // 解析 JSON 字符串为 JsonElement
                    JsonElement jsonElement = JsonParser.parseString(Objects.requireNonNull(FileManager.getTemplateContent(file.getPath())));
                    if (type.equals("int"))
                        field.set(null,Integer.valueOf(jsonElement.getAsJsonObject().get(name).getAsString()));
                    if (type.equals("float"))
                        field.set(null,Float.valueOf(jsonElement.getAsJsonObject().get(name).getAsString()));
                    if (type.equals("String"))
                        field.set(null,String.valueOf(jsonElement.getAsJsonObject().get(name).getAsString()));
                    if (type.equals("bool"))
                        field.set(null, Boolean.valueOf(jsonElement.getAsJsonObject().get(name).getAsString()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void writeFile(File file){
        Class<Config> clazz = Config.class;
        Field[] fields = clazz.getDeclaredFields();
        JsonObject json = new JsonObject ();
        for (Field field : fields) {
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                try {
                    // 设置可访问性，以便获取私有字段的值
                    field.setAccessible(true);
                    // 获取静态字段的值
                    Object value = field.get(null);
                    String name = field.getName();
                    if (name.equals("logger")) continue;
                    json.addProperty(name, String.valueOf(value));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        Gson gson = new Gson();
        String jsonString = gson.toJson(json);
        try {
            FileManager.writefile(file,jsonString);
        } catch (IOException e) {
            logger.error(Trace.getStackTraceAsString(e));
        }
    }
    public static void setDefault(){
        Language= "zh_cn";
    }
}
