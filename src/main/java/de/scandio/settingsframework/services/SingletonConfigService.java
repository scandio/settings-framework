package de.scandio.settingsframework.services;

import de.scandio.settingsframework.configs.SingletonConfig;
import de.scandio.settingsframework.settings.Config;
import de.scandio.settingsframework.settings.FlagConfig;

public class SingletonConfigService implements ConfigService {

    @Override
    public Config getConfig() {
        return SingletonConfig.getConfig();
    }

    @Override
    public Config getConfig(String configKey) {
        return SingletonConfig.getConfig(configKey);
    }

    @Override
    public FlagConfig getFlagConfig() {
        return SingletonConfig.getFlagConfig();
    }
}
