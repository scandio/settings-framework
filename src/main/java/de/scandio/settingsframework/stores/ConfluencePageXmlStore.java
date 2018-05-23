package de.scandio.settingsframework.stores;

import com.atlassian.confluence.core.ContentPropertyManager;
import com.atlassian.confluence.pages.Page;

public class ConfluencePageXmlStore extends AbstractXmlStore {

    private final ContentPropertyManager contentPropertyManager;
    private final Page page;

    public ConfluencePageXmlStore(String storageKey, Page page, ContentPropertyManager contentPropertyManager) {
        super(storageKey);
        this.page = page;
        this.contentPropertyManager = contentPropertyManager;
    }

    @Override
    protected String loadValuesXml() {
        return contentPropertyManager.getTextProperty(page, storageKey);
    }

    @Override
    protected void removeValuesXml() {
        contentPropertyManager.removeProperty(page, storageKey);
    }

    @Override
    protected void storeValuesXml(String valuesXml) {
        contentPropertyManager.setTextProperty(page, storageKey, valuesXml);
    }
}
