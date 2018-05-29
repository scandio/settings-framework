package de.scandio.settingsframework.services;

import de.scandio.settingsframework.settings.Config;

public interface ConfigService {

    Config getConfig();

    Config getConfig(String configKey);
}
