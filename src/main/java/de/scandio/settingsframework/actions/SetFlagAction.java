package de.scandio.settingsframework.actions;

import com.atlassian.confluence.core.ConfluenceActionSupport;
import com.atlassian.confluence.security.Permission;
import com.atlassian.confluence.security.PermissionManager;
import com.atlassian.confluence.user.AuthenticatedUserThreadLocal;
import com.atlassian.confluence.user.ConfluenceUser;
import de.scandio.settingsframework.services.SettingsService;

public class SetFlagAction extends ConfluenceActionSupport {

    private SettingsService settingsService;

    private String flagName;
    private String flagValue;

    @Override
    public String execute() throws Exception {
        settingsService.getFlags().setValue(flagName, flagValue.equals("true"));
        return SUCCESS;
    }

    @Override
    public boolean isPermitted() {
        ConfluenceUser user = AuthenticatedUserThreadLocal.get();
        return permissionManager.hasPermission(user, Permission.ADMINISTER, PermissionManager.TARGET_SYSTEM);
    }

    public String getFlagName() {
        return flagName;
    }

    public void setFlagName(String flagName) {
        this.flagName = flagName;
    }

    public String getFlagValue() {
        return flagValue;
    }

    public void setFlagValue(String flagValue) {
        this.flagValue = flagValue;
    }

    public void setSettingsService(SettingsService settingsService) {
        this.settingsService = settingsService;
    }
}
