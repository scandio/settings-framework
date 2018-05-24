package de.scandio.settingsframework.services;

import com.atlassian.bandana.BandanaManager;
import de.scandio.settingsframework.settings.Store;
import de.scandio.settingsframework.stores.ConfluenceXmlStore;


public abstract class ConfluenceMultiSettingsService extends AbstractMultiSettingsService implements SettingsService {

    protected BandanaManager bandanaManager;

    @Override
    protected Store getStore() {
        return new ConfluenceXmlStore(bandanaManager);
    }

    public void setBandanaManager(BandanaManager bandanaManager) {
        this.bandanaManager = bandanaManager;
    }
}
