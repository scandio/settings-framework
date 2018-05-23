package de.scandio.settingsframework.settings;

import java.util.List;
import java.util.Map;

public interface Config {

    String getStorageKey();

    Map<String, String> getDefaultValues();

    Map<String, String> getMasks();

    List<Migrator> getMigrators();
}
