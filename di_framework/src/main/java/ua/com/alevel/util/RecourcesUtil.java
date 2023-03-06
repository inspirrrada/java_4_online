package ua.com.alevel.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class RecourcesUtil {

    private RecourcesUtil() { }

    public static Map<String, String> getRecources(ClassLoader mainClassLoader) {
        try(InputStream inputStream = mainClassLoader.getResourceAsStream("application.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            Map<String, String> propertiesMap = new HashMap<>();
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                propertiesMap.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            }
            return propertiesMap;
        } catch (IOException e) {
            throw new RuntimeException("Error! File was not loaded!");
        }
    }
}
