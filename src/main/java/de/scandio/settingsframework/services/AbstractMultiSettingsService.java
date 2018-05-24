package de.scandio.settingsframework.services;

import de.scandio.settingsframework.settings.Config;
import de.scandio.settingsframework.settings.Settings;
import de.scandio.settingsframework.settings.Store;


public abstract class AbstractMultiSettingsService implements SettingsService {

    protected MultiConfigService multiConfigService;

    @Override
    public Settings getSettings() {
        String configKey = this.getConfigKey();
        Config config = multiConfigService.getConfig(configKey);
        Store store = this.getStore();
        return new Settings(store, config);
    }

    protected abstract Store getStore();

    protected abstract String getConfigKey();

    public void setMultiConfigService(MultiConfigService multiConfigService) {
        this.multiConfigService = multiConfigService;
    }
}
