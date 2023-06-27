package org.eu.hanana.cirno.csgo.loader;

import org.eu.hanana.cirno.csgo.api.Plugin;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PluginLoader {
    private static PluginLoader Instance;
    public Map<String,PluginProp> plugins=new HashMap<>();
    public List<Class<?>> getClassesWithAnnotation(Class<? extends Annotation> annotation) {


        // 遍历所有的类
        for (Package pkg : Package.getPackages()) {
            Class<?> clazz=pkg.getClass();
            if (clazz.isAnnotationPresent(annotation)){

            }
        }

        return classes;
    }
    public PluginLoader(){
        Instance=this;
        getClassesWithAnnotation(Plugin.class);
    }
    public static PluginLoader getInstance(){ return Instance; }
}
