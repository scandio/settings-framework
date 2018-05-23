package de.scandio.settingsframework.services.impl;

import com.atlassian.bandana.BandanaManager;
import de.scandio.settingsframework.services.SettingsService;
import de.scandio.settingsframework.settings.Settings;
import de.scandio.settingsframework.stores.ConfluenceXmlStore;


public class ConfluenceSettingsServiceImpl implements SettingsService {

    protected BandanaManager bandanaManager;

    @Override
    public Settings getSettings() {
        return new Settings(new ConfluenceXmlStore(bandanaManager));
    }

    public void setBandanaManager(BandanaManager bandanaManager) {
        this.bandanaManager = bandanaManager;
    }
}
