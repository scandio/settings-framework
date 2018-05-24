package de.scandio.settingsframework.rest;

import com.atlassian.confluence.security.Permission;
import com.atlassian.confluence.security.PermissionManager;
import com.atlassian.confluence.user.AuthenticatedUserThreadLocal;
import com.atlassian.confluence.user.ConfluenceUser;
import de.scandio.settingsframework.services.MultiSettingsService;
import de.scandio.settingsframework.services.SettingsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("settings")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class ConfluenceMultiSettingsResource {

    private MultiSettingsService multiSettingsService;
    private PermissionManager permissionManager;

    @GET
    @Path("{configKey}")
    public Response getSettings(@PathParam("configKey") String configKey) {
        if (userIsNotAdministrator()) {
            return Response.status(404).build();
        }

        Map<String, String> settings = multiSettingsService.getSettings(configKey).getMaskedValues();
        return Response.ok(settings).build();
    }

    @PUT
    @Path("{configKey}")
    public Response setSettings(@PathParam("configKey") String configKey, Map<String, String> newSettings) {
        if (userIsNotAdministrator()) {
            return Response.status(404).build();
        }

        multiSettingsService.getSettings(configKey).setValues((newSettings));
        Map<String, String> settings = multiSettingsService.getSettings(configKey).getMaskedValues();
        return Response.ok(settings).build();
    }

    private boolean userIsNotAdministrator() {
        ConfluenceUser user = AuthenticatedUserThreadLocal.get();
        return user == null
                || !permissionManager.hasPermission(user, Permission.ADMINISTER, PermissionManager.TARGET_SYSTEM);
    }

    public void setPermissionManager(PermissionManager permissionManager) {
        this.permissionManager = permissionManager;
    }

    public void setMultiSettingsService(MultiSettingsService multiSettingsService) {
        this.multiSettingsService = multiSettingsService;
    }
}
