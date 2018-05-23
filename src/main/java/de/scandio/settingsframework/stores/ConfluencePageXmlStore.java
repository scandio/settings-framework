package de.scandio.settingsframework.stores;

import com.atlassian.confluence.core.ContentPropertyManager;
import com.atlassian.confluence.pages.Page;

public class ConfluencePageXmlStore extends AbstractXmlStore {

    private final ContentPropertyManager contentPropertyManager;
    private final Page page;

    public ConfluencePageXmlStore(Page page, ContentPropertyManager contentPropertyManager) {
        this.page = page;
        this.contentPropertyManager = contentPropertyManager;
    }

    @Override
    protected String loadValuesXml(String storageKey) {
        return contentPropertyManager.getTextProperty(page, storageKey);
    }

    @Override
    protected void removeValuesXml(String storageKey) {
        contentPropertyManager.removeProperty(page, storageKey);
    }

    @Override
    protected void storeValuesXml(String storageKey, String valuesXml) {
        contentPropertyManager.setTextProperty(page, storageKey, valuesXml);
    }
}
