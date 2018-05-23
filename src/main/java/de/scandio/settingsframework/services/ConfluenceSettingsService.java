package de.scandio.settingsframework.services;

import com.atlassian.confluence.pages.Page;
import com.atlassian.confluence.user.ConfluenceUser;
import de.scandio.settingsframework.settings.Settings;

public interface ConfluenceSettingsService {

    Settings getGlobalSettings(String storageKey);

    Settings getUserSettings(ConfluenceUser user, String storageKey);

    Settings getSpaceSettings(String spaceKey, String storageKey);

    Settings getPageSettings(Page page, String storageKey);

}
