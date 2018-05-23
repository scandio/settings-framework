package de.scandio.settingsframework.settings;

import java.util.Map;

public interface Migrator {

    Map<String, String> migrate(Map<String, String> values);
}
