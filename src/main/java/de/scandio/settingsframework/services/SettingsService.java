package de.scandio.settingsframework.services;

import de.scandio.settingsframework.settings.Settings;

public interface SettingsService {

    Settings getSettings();

    Settings getSettings(String configKey);

}
