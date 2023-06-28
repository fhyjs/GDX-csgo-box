package org.eu.hanana.cirno.csgo.loader;

import com.sun.tools.javac.main.Option;
import org.eu.hanana.cirno.csgo.api.Plugin;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PluginLoader {
    private static PluginLoader Instance;
    public List<PluginProp> plugins=new ArrayList<>();
    public PluginLoader(){
        Instance=this;
        for (Package aPackage : Package.getPackages()) {
            List<Class<?>> classes = findAllClassesUsingClassLoader(aPackage.getName()); // 指定包名
            // 输出所有类的名称
            for (Class<?> clazz : classes) {
                if (clazz.isAnnotationPresent(Plugin.class)){
                    for (Annotation annotation : clazz.getAnnotations()) {
                        if (annotation instanceof Plugin){
                            System.out.println("found plugin class at"+clazz);
                            PluginProp pp = new PluginProp();
                            pp.id=((Plugin) annotation).id();
                            pp.pluginClass=clazz;
                            plugins.add(pp);
                            break;
                        }
                    }
                }
            }
        }

        for (PluginProp plugin : plugins) {
            try {
                plugin.instance=plugin.pluginClass.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static PluginLoader getInstance(){ return Instance; }
    public List<Class<?>> findAllClassesUsingClassLoader(String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        return new ArrayList<>(reflections.getSubTypesOf(Object.class));
    }
}
