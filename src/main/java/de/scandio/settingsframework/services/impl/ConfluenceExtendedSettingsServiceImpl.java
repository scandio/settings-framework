package de.scandio.settingsframework.services.impl;

import com.atlassian.confluence.core.ContentPropertyManager;
import com.atlassian.confluence.pages.Page;
import com.atlassian.confluence.user.ConfluenceUser;
import com.atlassian.confluence.user.PersonalInformationManager;
import de.scandio.settingsframework.services.ConfluenceExtendedSettingsService;
import de.scandio.settingsframework.settings.Settings;
import de.scandio.settingsframework.stores.ConfluencePageXmlStore;
import de.scandio.settingsframework.stores.ConfluenceSpaceXmlStore;
import de.scandio.settingsframework.stores.ConfluenceUserXmlStore;


public class ConfluenceExtendedSettingsServiceImpl extends ConfluenceSettingsServiceImpl implements ConfluenceExtendedSettingsService {

    private PersonalInformationManager personalInformationManager;
    private ContentPropertyManager contentPropertyManager;

    @Override
    public Settings getUserSettings(ConfluenceUser user) {
        return new Settings(new ConfluenceUserXmlStore(user, personalInformationManager, contentPropertyManager));
    }

    @Override
    public Settings getSpaceSettings(String spaceKey) {
        return new Settings(new ConfluenceSpaceXmlStore(spaceKey, bandanaManager));
    }

    @Override
    public Settings getPageSettings(Page page) {
        return new Settings(new ConfluencePageXmlStore(page, contentPropertyManager));
    }

    public void setPersonalInformationManager(PersonalInformationManager personalInformationManager) {
        this.personalInformationManager = personalInformationManager;
    }

    public void setContentPropertyManager(ContentPropertyManager contentPropertyManager) {
        this.contentPropertyManager = contentPropertyManager;
    }
}
