package de.scandio.settingsframework.stores;

import com.atlassian.bandana.BandanaContext;
import com.atlassian.bandana.BandanaManager;
import com.atlassian.confluence.setup.bandana.ConfluenceBandanaContext;

public class ConfluenceGlobalXmlStore extends AbstractXmlStore {

    private final BandanaManager bandanaManager;
    private final BandanaContext bandanaContext;

    public ConfluenceGlobalXmlStore(String storageKey, BandanaManager bandanaManager) {
        super(storageKey);
        this.bandanaManager = bandanaManager;
        this.bandanaContext = new ConfluenceBandanaContext();
    }

    @Override
    protected String loadValuesXml() {
        return (String) bandanaManager.getValue(bandanaContext, storageKey);
    }

    @Override
    protected void removeValuesXml() {
        bandanaManager.removeValue(bandanaContext, storageKey);
    }

    @Override
    protected void storeValuesXml(String valuesXml) {
        bandanaManager.setValue(bandanaContext, storageKey, valuesXml);
    }
}
