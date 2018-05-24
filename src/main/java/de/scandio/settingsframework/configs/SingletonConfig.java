package de.scandio.settingsframework.configs;

import de.scandio.settingsframework.settings.Config;

public class SingletonConfig {

    private static Config config = null;

    public static Config getConfig() {
        if(config == null) {
            config = new Config();
        }
        return config;
    }
}
