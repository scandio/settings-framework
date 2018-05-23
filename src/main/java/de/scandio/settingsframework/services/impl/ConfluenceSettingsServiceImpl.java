package de.scandio.settingsframework.services.impl;

import com.atlassian.bandana.BandanaManager;
import com.atlassian.confluence.core.ContentPropertyManager;
import com.atlassian.confluence.pages.Page;
import com.atlassian.confluence.user.ConfluenceUser;
import com.atlassian.confluence.user.PersonalInformationManager;
import de.scandio.settingsframework.services.ConfluenceSettingsService;
import de.scandio.settingsframework.stores.*;
import de.scandio.settingsframework.settings.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ConfluenceSettingsServiceImpl implements ConfluenceSettingsService {

    private final static Logger log = LoggerFactory.getLogger(ConfluenceSettingsServiceImpl.class);

    private PersonalInformationManager personalInformationManager;
    private ContentPropertyManager contentPropertyManager;
    private BandanaManager bandanaManager;

    @Override
    public Settings getGlobalSettings(String storageKey) {
        return new Settings(new ConfluenceGlobalXmlStore(storageKey, bandanaManager));
    }

    @Override
    public Settings getUserSettings(ConfluenceUser user, String storageKey) {
        return new Settings(new ConfluenceUserXmlStore(storageKey, user, personalInformationManager, contentPropertyManager));
    }

    @Override
    public Settings getSpaceSettings(String spaceKey, String storageKey) {
        return new Settings(new ConfluenceSpaceXmlStore(storageKey, spaceKey, bandanaManager));
    }

    @Override
    public Settings getPageSettings(Page page, String storageKey) {
        return new Settings(new ConfluencePageXmlStore(storageKey, page, contentPropertyManager));
    }

    public void setPersonalInformationManager(PersonalInformationManager personalInformationManager) {
        this.personalInformationManager = personalInformationManager;
    }

    public void setContentPropertyManager(ContentPropertyManager contentPropertyManager) {
        this.contentPropertyManager = contentPropertyManager;
    }

    public void setBandanaManager(BandanaManager bandanaManager) {
        this.bandanaManager = bandanaManager;
    }
}
