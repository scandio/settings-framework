package de.scandio.settingsframework.services;

import de.scandio.settingsframework.configs.SingletonMultiConfig;
import de.scandio.settingsframework.settings.Config;

public class SingletonMultiConfigService implements MultiConfigService {

    @Override
    public Config getConfig(String configKey) {
        return SingletonMultiConfig.getInstance(configKey);
    }
}
