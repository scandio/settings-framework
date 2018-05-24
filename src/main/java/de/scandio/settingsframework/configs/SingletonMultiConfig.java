package de.scandio.settingsframework.configs;

import de.scandio.settingsframework.settings.Config;

import java.util.HashMap;
import java.util.Map;

public class SingletonMultiConfig {

    private static Map<String, Config> instancesByKey = new HashMap<>();

    public static Config getInstance(String key) {
        instancesByKey.putIfAbsent(key, new Config());
        return instancesByKey.get(key);
    }
}
