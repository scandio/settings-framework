package de.scandio.settingsframework.services;

import de.scandio.settingsframework.settings.Config;
import de.scandio.settingsframework.settings.FlagConfig;

public interface ConfigService {

    Config getConfig();

    Config getConfig(String configKey);

    FlagConfig getFlagConfig();
}
