package ua.com.alevel;

import java.util.Set;
import java.util.stream.Collectors;

public class DIFrameworkContext {
    private DIFrameworkSearcher searcher;
    private Set<Class<?>> serviceInterfaces;

    public DIFrameworkContext(Class<?> mainClass) {
        Package aPackage = mainClass.getPackage();
        this.searcher = new DIFrameworkSearcher(mainClass.getPackageName());
        this.serviceInterfaces = this.searcher.getInterfaces()
                .stream()
                .map(className -> {
                    try {
                        return Class.forName(className);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(Class::isInterface)
                .collect(Collectors.toSet());
    }
}
