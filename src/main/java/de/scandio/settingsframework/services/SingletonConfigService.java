package de.scandio.settingsframework.services;

import de.scandio.settingsframework.configs.SingletonConfig;
import de.scandio.settingsframework.services.ConfigService;
import de.scandio.settingsframework.settings.Config;

public class SingletonConfigService implements ConfigService {

    @Override
    public Config getConfig() {
        return SingletonConfig.getConfig();
    }

    @Override
    public Config getConfig(String configKey) {
        return SingletonConfig.getConfig(configKey);
    }
}
