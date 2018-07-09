package de.scandio.settingsframework.services;

import com.atlassian.bandana.BandanaManager;
import com.atlassian.confluence.core.ContentPropertyManager;
import com.atlassian.confluence.pages.Page;
import com.atlassian.confluence.user.ConfluenceUser;
import com.atlassian.confluence.user.PersonalInformationManager;
import de.scandio.settingsframework.settings.Config;
import de.scandio.settingsframework.settings.Settings;
import de.scandio.settingsframework.settings.Store;
import de.scandio.settingsframework.stores.ConfluencePageXmlStore;
import de.scandio.settingsframework.stores.ConfluenceSpaceXmlStore;
import de.scandio.settingsframework.stores.ConfluenceUserXmlStore;
import de.scandio.settingsframework.stores.ConfluenceXmlStore;

public class ConfluenceSettingsService extends AbstractSettingsService implements SettingsService {

    private BandanaManager bandanaManager;
    private PersonalInformationManager personalInformationManager;
    private ContentPropertyManager contentPropertyManager;

    @Override
    protected Store getStore() {
        return new ConfluenceXmlStore(bandanaManager);
    }

    public Settings getUserSettings(ConfluenceUser user, String configKey) {
        Config config = configService.getConfig(configKey);
        Store store = new ConfluenceUserXmlStore(user, personalInformationManager, contentPropertyManager);
        return new Settings(store, config);
    }

    public Settings getSpaceSettings(String spaceKey, String configKey) {
        Config config = configService.getConfig(configKey);
        Store store = new ConfluenceSpaceXmlStore(spaceKey, bandanaManager);
        return new Settings(store, config);
    }

    public Settings getPageSettings(Page page, String configKey) {
        Config config = configService.getConfig(configKey);
        Store store = new ConfluencePageXmlStore(page, contentPropertyManager);
        return new Settings(store, config);
    }

    public void setBandanaManager(BandanaManager bandanaManager) {
        this.bandanaManager = bandanaManager;
    }

    public void setPersonalInformationManager(PersonalInformationManager personalInformationManager) {
        this.personalInformationManager = personalInformationManager;
    }

    public void setContentPropertyManager(ContentPropertyManager contentPropertyManager) {
        this.contentPropertyManager = contentPropertyManager;
    }
}
