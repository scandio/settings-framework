package de.scandio.settingsframework.services;

import de.scandio.settingsframework.settings.Config;
import de.scandio.settingsframework.settings.Settings;
import de.scandio.settingsframework.settings.Store;


public abstract class AbstractMultiSettingsService implements MultiSettingsService {

    protected MultiConfigService multiConfigService;

    @Override
    public Settings getSettings(String configKey) {
        Config config = multiConfigService.getConfig(configKey);
        Store store = this.getStore();
        return config != null && store != null
                ? new Settings(store, config)
                : null;
    }

    protected abstract Store getStore();

    public void setMultiConfigService(MultiConfigService multiConfigService) {
        this.multiConfigService = multiConfigService;
    }
}
