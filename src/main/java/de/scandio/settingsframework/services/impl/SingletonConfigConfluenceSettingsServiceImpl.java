package de.scandio.settingsframework.services.impl;

import de.scandio.settingsframework.configs.SingletonConfig;
import de.scandio.settingsframework.services.SettingsService;
import de.scandio.settingsframework.settings.Settings;

public class SingletonConfigConfluenceSettingsServiceImpl extends ConfluenceSettingsServiceImpl implements SettingsService {

    @Override
    public Settings getSettings() {
        return super.getSettings()
                .withConfig(SingletonConfig.getInstance());
    }
}
