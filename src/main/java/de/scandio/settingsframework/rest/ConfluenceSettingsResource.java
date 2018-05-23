package de.scandio.settingsframework.rest;

import com.atlassian.confluence.security.Permission;
import com.atlassian.confluence.security.PermissionManager;
import com.atlassian.confluence.user.AuthenticatedUserThreadLocal;
import com.atlassian.confluence.user.ConfluenceUser;
import de.scandio.settingsframework.services.SettingsService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("settings")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class ConfluenceSettingsResource {

    private SettingsService settingsService;
    private PermissionManager permissionManager;

    @GET
    public Response getSettings(@Context HttpServletRequest httpServletRequest) {
        Map<String, String> settings = settingsService.getSettings().getMaskedValues();
        return Response.ok(settings).build();
    }

    @PUT
    public Response setSettings(@Context HttpServletRequest httpServletRequest, Map<String, String> newSettings) {
        ConfluenceUser user = AuthenticatedUserThreadLocal.get();
        boolean userIsNotAdministrator = user == null
                || !permissionManager.hasPermission(user, Permission.ADMINISTER, PermissionManager.TARGET_SYSTEM);

        if (userIsNotAdministrator) {
            return Response.status(404).build();
        }

        settingsService.getSettings().setValues((newSettings));
        Map<String, String> settings = settingsService.getSettings().getMaskedValues();
        return Response.ok(settings).build();
    }

    public void setPermissionManager(PermissionManager permissionManager) {
        this.permissionManager = permissionManager;
    }

    public void setSettingsService(SettingsService settingsService) {
        this.settingsService = settingsService;
    }
}
