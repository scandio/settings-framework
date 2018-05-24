package de.scandio.settingsframework.services;

import de.scandio.settingsframework.settings.Settings;

public interface MultiSettingsService {

    Settings getSettings(String configKey);

}
