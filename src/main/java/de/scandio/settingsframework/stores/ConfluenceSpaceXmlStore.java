package de.scandio.settingsframework.stores;

import com.atlassian.bandana.BandanaContext;
import com.atlassian.bandana.BandanaManager;
import com.atlassian.confluence.setup.bandana.ConfluenceBandanaContext;

public class ConfluenceSpaceXmlStore extends AbstractXmlStore {

    private final BandanaManager bandanaManager;
    private final BandanaContext bandanaContext;

    public ConfluenceSpaceXmlStore(String spaceKey, BandanaManager bandanaManager) {
        this.bandanaContext = new ConfluenceBandanaContext(spaceKey);
        this.bandanaManager = bandanaManager;
    }

    @Override
    protected String loadValuesXml(String storageKey) {
        return (String) bandanaManager.getValue(bandanaContext, storageKey);
    }

    @Override
    protected void removeValuesXml(String storageKey) {
        bandanaManager.removeValue(bandanaContext, storageKey);
    }

    @Override
    protected void storeValuesXml(String storageKey, String valuesXml) {
        bandanaManager.setValue(bandanaContext, storageKey, valuesXml);
    }
}
