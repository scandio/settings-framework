package de.scandio.settingsframework.services;

import com.atlassian.confluence.pages.Page;
import com.atlassian.confluence.user.ConfluenceUser;
import de.scandio.settingsframework.settings.Settings;

public interface ConfluenceExtendedSettingsService extends SettingsService {

    Settings getUserSettings(ConfluenceUser user);

    Settings getSpaceSettings(String spaceKey);

    Settings getPageSettings(Page page);

}
