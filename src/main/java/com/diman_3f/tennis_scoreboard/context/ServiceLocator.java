package com.diman_3f.tennis_scoreboard.context;

import java.util.HashMap;
import java.util.Map;

public final class ServiceLocator {

    private static final Map<Class<?>, Object> services = new HashMap<>();

    public static <T> T getService(Class<T> serviceClass) {
        return serviceClass.cast(services.get(serviceClass));
    }

    public static void registerService(Object service) {
        services.put(service.getClass(), service);
    }
}
