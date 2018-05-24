package de.scandio.settingsframework.services.impl;

import com.atlassian.confluence.core.ContentPropertyManager;
import com.atlassian.confluence.pages.Page;
import com.atlassian.confluence.user.ConfluenceUser;
import com.atlassian.confluence.user.PersonalInformationManager;
import de.scandio.settingsframework.services.ConfigService;
import de.scandio.settingsframework.services.ConfluenceExtendedSettingsService;
import de.scandio.settingsframework.settings.Config;
import de.scandio.settingsframework.settings.Settings;
import de.scandio.settingsframework.settings.Store;
import de.scandio.settingsframework.stores.ConfluencePageXmlStore;
import de.scandio.settingsframework.stores.ConfluenceSpaceXmlStore;
import de.scandio.settingsframework.stores.ConfluenceUserXmlStore;


public class ConfluenceExtendedSettingsServiceImpl extends ConfluenceSettingsService implements ConfluenceExtendedSettingsService {

    protected ConfigService configService;
    protected PersonalInformationManager personalInformationManager;
    protected ContentPropertyManager contentPropertyManager;

    @Override
    public Settings getUserSettings(ConfluenceUser user) {
        Config config = configService.getConfig();
        Store store = new ConfluenceUserXmlStore(user, personalInformationManager, contentPropertyManager);
        return new Settings(store, config);
    }

    @Override
    public Settings getSpaceSettings(String spaceKey) {
        Config config = configService.getConfig();
        Store store = new ConfluenceSpaceXmlStore(spaceKey, bandanaManager);
        return new Settings(store, config);
    }

    @Override
    public Settings getPageSettings(Page page) {
        Config config = configService.getConfig();
        Store store = new ConfluencePageXmlStore(page, contentPropertyManager);
        return new Settings(store, config);
    }

    @Override
    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }

    public void setPersonalInformationManager(PersonalInformationManager personalInformationManager) {
        this.personalInformationManager = personalInformationManager;
    }

    public void setContentPropertyManager(ContentPropertyManager contentPropertyManager) {
        this.contentPropertyManager = contentPropertyManager;
    }
}
