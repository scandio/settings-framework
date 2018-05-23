package de.scandio.settingsframework.settings;

import java.util.Map;

/**
 * @author Georg Schmidl
 */
public interface SettingsMigrator {

    Map<String, String> migrate(Map<String, String> values);
}
