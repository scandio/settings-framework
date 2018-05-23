package de.scandio.settingsframework.settings;

/**
 * @author Georg Schmidl
 */
public interface SettingMigrator {

    String migrate(String value);
}
