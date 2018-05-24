package de.scandio.settingsframework.configs;

import de.scandio.settingsframework.settings.Config;

public class SingletonConfig {

    private static Config instance = null;

    public static Config getInstance() {
        if(instance == null) {
            instance = new Config();
        }
        return instance;
    }
}
