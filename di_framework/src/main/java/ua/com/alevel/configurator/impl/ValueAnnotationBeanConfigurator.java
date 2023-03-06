package ua.com.alevel.configurator.impl;

import ua.com.alevel.DIFrameworkApplication;
import ua.com.alevel.annotations.Value;
import ua.com.alevel.configurator.BeanConfigurator;

import java.lang.reflect.Field;
import java.util.Map;

public class ValueAnnotationBeanConfigurator implements BeanConfigurator {

    private final Map<String, String> propertiesMap = DIFrameworkApplication.getPropertiesMap();
    @Override
    public void configure(Object bean) {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Value.class)) {
                Value property = field.getAnnotation(Value.class);
                String value = property.value();
                String props = propertiesMap.get(value);
                field.setAccessible(true);
                try {
                    field.set(bean, props);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw new RuntimeException();
                }
;            }
        }

    }
}
