package de.scandio.settingsframework.services.impl;

import de.scandio.settingsframework.configs.SingletonConfig;
import de.scandio.settingsframework.services.ConfigService;
import de.scandio.settingsframework.settings.Config;

public class SingletonConfigService implements ConfigService {

    @Override
    public Config getConfig() {
        return SingletonConfig.getInstance();
    }
}
