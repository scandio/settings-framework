package de.scandio.settingsframework.services.impl;

import com.atlassian.bandana.BandanaManager;
import de.scandio.settingsframework.services.ConfigService;
import de.scandio.settingsframework.services.SettingsService;
import de.scandio.settingsframework.settings.Config;
import de.scandio.settingsframework.settings.Settings;
import de.scandio.settingsframework.settings.Store;
import de.scandio.settingsframework.stores.ConfluenceXmlStore;


public class ConfluenceSettingsServiceImpl implements SettingsService {

    protected ConfigService configService;
    protected BandanaManager bandanaManager;

    @Override
    public Settings getSettings() {
        Config config = configService.getConfig();
        Store store = new ConfluenceXmlStore(bandanaManager);
        return new Settings(store, config);
    }

    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }

    public void setBandanaManager(BandanaManager bandanaManager) {
        this.bandanaManager = bandanaManager;
    }
}
