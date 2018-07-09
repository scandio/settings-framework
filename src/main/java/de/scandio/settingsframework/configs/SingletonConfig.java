package de.scandio.settingsframework.configs;

import de.scandio.settingsframework.settings.Config;
import de.scandio.settingsframework.settings.FlagConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingletonConfig {

    private static Config config = null;
    private static FlagConfig flagConfig = null;

    private static Map<String, Config> configsByKey = new HashMap<>();
    private static List<String> allowedKeys;

    public static Config getConfig() {
        if(config == null) {
            config = new Config();
        }
        return config;
    }

    public static Config getConfig(String key) {
        if (allowedKeys == null || allowedKeys.contains(key)) {
            configsByKey.putIfAbsent(key, new Config());
        }
        return configsByKey.get(key);
    }

    public static FlagConfig getFlagConfig() {
        if(flagConfig == null) {
            flagConfig = new FlagConfig();
        }
        return flagConfig;
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
        SingletonConfig.addAllowedKey(key);
        return SingletonConfig.getConfig(key);
    }
}
