package de.scandio.settingsframework.services;

import de.scandio.settingsframework.settings.Config;
import de.scandio.settingsframework.settings.Settings;
import de.scandio.settingsframework.settings.Store;


public abstract class AbstractSettingsService implements SettingsService {

    protected ConfigService configService;

    @Override
    public Settings getSettings() {
        Config config = configService.getConfig();
        Store store = this.getStore();
        return new Settings(store, config);
    }

    protected abstract Store getStore();

    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }
}
