package de.scandio.settingsframework.services;

import de.scandio.settingsframework.settings.Config;

public interface MultiConfigService {

    Config getConfig(String configKey);
}
