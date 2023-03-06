package ua.com.alevel;

import ua.com.alevel.util.RecourcesUtil;

import java.util.Map;

public class DIFrameworkApplication {

    private static Map<String, String> propertiesMap;
    public static void start(Class<?> mainClass) {
        propertiesMap = RecourcesUtil.getRecources(mainClass.getClassLoader());
        DIFrameworkContext context = new DIFrameworkContext(mainClass);
        BeanFactory beanFactory = new BeanFactory(context.getServiceInterfaces(), context.getSearcher().getScanner());
        beanFactory.configure();
        DIFrameworkStarter diFrameworkStarter = new DIFrameworkStarter();
        diFrameworkStarter.start();
    }

    public static Map<String, String> getPropertiesMap() {
        return propertiesMap;
    }
}
