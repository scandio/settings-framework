package de.scandio.settingsframework.stores;

import com.atlassian.confluence.core.ContentPropertyManager;
import com.atlassian.confluence.user.ConfluenceUser;
import com.atlassian.confluence.user.PersonalInformation;
import com.atlassian.confluence.user.PersonalInformationManager;

public class ConfluenceUserXmlStore extends AbstractXmlStore {

    private final PersonalInformationManager personalInformationManager;
    private final ContentPropertyManager contentPropertyManager;

    private final PersonalInformation personalInformation;

    public ConfluenceUserXmlStore(ConfluenceUser user, PersonalInformationManager personalInformationManager, ContentPropertyManager contentPropertyManager) {
        this.personalInformationManager = personalInformationManager;
        this.contentPropertyManager = contentPropertyManager;
        this.personalInformation = this.personalInformationManager.getOrCreatePersonalInformation(user);
    }

    @Override
    protected String loadValuesXml(String storageKey) {
        return contentPropertyManager.getTextProperty(personalInformation, storageKey);
    }

    @Override
    protected void removeValuesXml(String storageKey) {
        contentPropertyManager.removeProperty(personalInformation, storageKey);
    }

    @Override
    protected void storeValuesXml(String storageKey, String valuesXml) {
        contentPropertyManager.setTextProperty(personalInformation, storageKey, valuesXml);
    }
}
