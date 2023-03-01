package ua.com.alevel;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanFactory {
    private Map<Class<?>, Object> beanMap = new HashMap<>();

    public BeanFactory(Set<Class<?>> interfaces, Reflections scanner) {
        initBeanMap(interfaces, scanner);
    }

    private void initBeanMap(Set<Class<?>> interfaces, Reflections scanner) {
        for (Class<?> anInterface : interfaces) {
            Set<Class<?>> subTypesOf = (Set<Class<?>>) scanner.getSubTypesOf(anInterface);
        }
    }
}
