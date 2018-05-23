package de.scandio.settingsframework.settings;

import java.util.Map;

public interface Store {

    Map<String, String> loadValues();

    void storeValues(Map<String, String> values);

    void removeValues();
}