package de.scandio.settingsframework.services;

import de.scandio.settingsframework.settings.*;

public abstract class AbstractSettingsService implements SettingsService {

    protected ConfigService configService;

    @Override
    public Settings getSettings() {
        Config config = configService.getConfig();
        Store store = this.getStore();
        return new Settings(store, config);
    }

    @Override
    public Settings getSettings(String configKey) {
        Config config = configService.getConfig(configKey);
        Store store = this.getStore();
        return config != null && store != null
                ? new Settings(store, config)
                : null;
    }

    @Override
    public Flags getFlags() {
        FlagConfig config = configService.getFlagConfig();
        Store store = this.getStore();
        return new Flags(store, config);
    }

    protected abstract Store getStore();

    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }
}
