package org.eu.hanana.cirno.csgo.res;

import com.badlogic.gdx.utils.Logger;
import org.eu.hanana.cirno.csgo.util.Trace;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileManager {
    private static final Logger logger=new Logger("FileManager");
    public static List<String> readAllResFiles(String domain,String path1) throws IOException {
        try {
            FileSystem filesystem = null;
            URL url = FileManager.class.getClassLoader().getResource(domain);
            //JOptionPane.showMessageDialog(null,url);
            if (url != null) {
                URI uri = url.toURI();
                Path path;

                if ("file".equals(uri.getScheme())) {
                    if(FileManager.class.getClassLoader().getResource(domain + "/" + path1).getPath().charAt(0)=='/')
                        path = Paths.get(FileManager.class.getClassLoader().getResource(domain + "/" + path1).getPath().substring(1));
                    else
                        path = Paths.get(FileManager.class.getClassLoader().getResource(domain + "/" + path1).getPath());
                } else {
                    if (!"jar".equals(uri.getScheme())) {
                        logger.error("Unsupported scheme " + uri + " trying to list all recipes");
                        return null;
                    }
                    filesystem=FileSystems.newFileSystem(uri, Collections.emptyMap());
                    path = filesystem.getPath("/"+domain+"/"+path1);
                }

                List<Path> list = Files.walk(path).collect(Collectors.toList());
                List<String> listS = new ArrayList<>();
                for (Path p:list){
                    String[] s=p.toString().replaceAll("\\\\","/").split("/");
                    if (Objects.equals(s[s.length - 1], "langs"))
                        continue;
                    listS.add(p.toString());
                }
                if (filesystem!=null && filesystem.isOpen()) filesystem.close();
                return listS;
            }
        }catch (Throwable e){
            logger.error(Trace.getStackTraceAsString(e));
        }
        return null;
    }

    public static List<String> recursiveReadFile(File fileOrDir) {
        List<String> t =new ArrayList<>();
        if (fileOrDir == null) {
            return null;
        }

        if (fileOrDir.isFile()) {
            t.add(fileOrDir.getPath());
        } else {
            for (File file : Objects.requireNonNull(fileOrDir.listFiles())) {
                t.addAll(recursiveReadFile(file));
            }
        }
        return t;
    }
    public static String getTemplateContent(String fn) throws Exception{
        if (fn.charAt(0)=='/')
            fn=fn.substring(1);
        URL url = FileManager.class.getClassLoader().getResource(fn);
        if (url==null) {
            File file = new File(fn);
            if (!file.exists()) {
                return null;
            }
            FileInputStream inputStream = new FileInputStream(file);
            int length = inputStream.available();
            byte[] bytes = new byte[length];
            inputStream.read(bytes);
            inputStream.close();
            return new String(bytes, StandardCharsets.UTF_8);
        }
        FileSystem filesystem = null;
        filesystem = FileSystems.newFileSystem(url.toURI(), Collections.emptyMap());
        Path path = filesystem.getPath(fn);
        String r = new String(Files.readAllBytes(path),StandardCharsets.UTF_8);
        if (filesystem.isOpen()) filesystem.close();
        return r;
    }
    public static byte[] getByteContent(String fn) throws Exception{
        if (fn.charAt(0)=='/')
            fn=fn.substring(1);
        fn=fn.replace("\\","/");
        URL url = FileManager.class.getClassLoader().getResource(fn);
        URI uri= null;
        if (url != null) {
            FileSystem filesystem = null;
            uri = url.toURI();
            Path path;
            if ("file".equals(uri.getScheme())) {
                if (FileManager.class.getClassLoader().getResource(fn).getPath().charAt(0) == '/')
                    path = Paths.get(FileManager.class.getClassLoader().getResource(fn).getPath().substring(1));
                else
                    path = Paths.get(FileManager.class.getClassLoader().getResource(fn).getPath());
            } else {
                if (!"jar".equals(uri.getScheme())) {
                    logger.error("Unsupported scheme " + uri + " trying to list all recipes");
                    return null;
                }
                filesystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
                path = filesystem.getPath(fn);
            }
            byte[] r = Files.readAllBytes(path);
            if (filesystem!=null&&filesystem.isOpen()) filesystem.close();
            return r;
        }
        return null;
    }
    public static String getJarPath() throws URISyntaxException {
        File jarFile = new File(FileManager.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        String jarPath = jarFile.getParent();
        return  jarPath+"/";
    }
    public static void RelAllRes() {
        // 获取当前类的类加载器
        ClassLoader classLoader = FileManager.class.getClassLoader();

        // 遍历所有资源
        try {
            // 获取所有资源的URL
            Enumeration<URL> resources = classLoader.getResources("");
            while (resources.hasMoreElements()) {
                URL resourceUrl = resources.nextElement();
                String protocol = resourceUrl.getProtocol();

                if ("jar".equals(protocol)) {
                    // JAR文件中的资源
                    extractResourcesFromJar(resourceUrl);
                } else if ("file".equals(protocol)) {
                    // 文件系统中的资源
                    extractResourcesFromFileSystem(resourceUrl);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String loadAsString(String file) {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String buffer = "";
            while ((buffer = reader.readLine()) != null) {
                result.append(buffer + '\n');
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
    private static void extractResourcesFromJar(URL resourceUrl) throws IOException {
        String jarPath = resourceUrl.getPath().substring(5, resourceUrl.getPath().indexOf("!"));
        try (FileSystem fileSystem = FileSystems.newFileSystem(URI.create(jarPath), new HashMap<>())) {
            Path root = fileSystem.getPath("/");
            extractResources(root);
        }
    }

    private static void extractResourcesFromFileSystem(URL resourceUrl) throws IOException {
        try {
            Path root = Paths.get(resourceUrl.toURI());
            extractResources(root);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static void extractResources(Path root) throws IOException {
        Files.walk(root)
                .filter(Files::isRegularFile)
                .forEach(file -> {
                    try (InputStream inputStream = Files.newInputStream(file)) {
                        if (file.getFileName().toString().contains(".class")) {
                            return;
                        }
                        String fileName = file.getFileName().toString();
                        Path outputPath = Paths.get(getJarPath() + Paths.get(fileName));
                        Files.copy(inputStream, outputPath, StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("Extracted resource: " + outputPath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
    public static void writefile(File file,String string) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(string);
        } catch (IOException e) {
            throw e;
        }
    }
}
