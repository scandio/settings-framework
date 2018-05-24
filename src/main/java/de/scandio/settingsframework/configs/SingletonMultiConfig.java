package de.scandio.settingsframework.configs;

import de.scandio.settingsframework.settings.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingletonMultiConfig {

    private static List<String> allowedKeys;
    private static Map<String, Config> configsByKey = new HashMap<>();

    public static Config getConfig(String key) {
        if (allowedKeys == null || allowedKeys.contains(key)) {
            configsByKey.putIfAbsent(key, new Config());
        }
        return configsByKey.get(key);
    }

    public static void addAllowedKey(String key) {
        if (allowedKeys == null) {
            allowedKeys = new ArrayList<>();
        }
        allowedKeys.add(key);
    }

    public static List<String> getAllowedKeys() {
        return allowedKeys;
    }

    public static Config allowAndGetConfig(String key) {
        SingletonMultiConfig.addAllowedKey(key);
        return SingletonMultiConfig.getConfig(key);
    }

}
