package de.scandio.settingsframework.services;

import com.atlassian.bandana.BandanaManager;
import de.scandio.settingsframework.services.ConfigService;
import de.scandio.settingsframework.services.SettingsService;
import de.scandio.settingsframework.settings.Config;
import de.scandio.settingsframework.settings.Settings;
import de.scandio.settingsframework.settings.Store;
import de.scandio.settingsframework.stores.ConfluenceXmlStore;


public class ConfluenceSettingsService extends AbstractSettingsService implements SettingsService {

    protected BandanaManager bandanaManager;

    @Override
    protected Store getStore() {
        return new ConfluenceXmlStore(bandanaManager);
    }

    public void setBandanaManager(BandanaManager bandanaManager) {
        this.bandanaManager = bandanaManager;
    }
}
