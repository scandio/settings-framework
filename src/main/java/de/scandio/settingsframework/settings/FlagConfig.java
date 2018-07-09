package de.scandio.settingsframework.settings;

import java.util.ArrayList;
import java.util.List;

public class FlagConfig {

    private String storageKey;
    private List<String> allowedFlags;

    public FlagConfig() {
    }

    public FlagConfig(String storageKey, List<String> allowedFlags) {
        this.storageKey = storageKey;
        this.allowedFlags = allowedFlags;
    }

    public String getStorageKey() {
        return storageKey;
    }

    public FlagConfig setStorageKey(String storageKey) {
        this.storageKey = storageKey;
        return this;
    }

    public List<String> getAllowedFlags() {
        return allowedFlags;
    }

    public FlagConfig setAllowedFlags(List<String> allowedFlags) {
        this.allowedFlags = allowedFlags;
        return this;
    }

    public FlagConfig addAllowedFlag(String key) {
        if (this.allowedFlags == null) {
            this.allowedFlags = new ArrayList<>();
        }
        this.allowedFlags.add(key);
        return this;
    }
}
