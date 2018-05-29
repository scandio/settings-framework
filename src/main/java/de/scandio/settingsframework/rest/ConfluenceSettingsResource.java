package de.scandio.settingsframework.rest;

import com.atlassian.confluence.security.Permission;
import com.atlassian.confluence.security.PermissionManager;
import com.atlassian.confluence.user.AuthenticatedUserThreadLocal;
import com.atlassian.confluence.user.ConfluenceUser;
import de.scandio.settingsframework.services.SettingsService;
import de.scandio.settingsframework.settings.Settings;

import javax.ws.rs.*;
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
    public Response getSettings() {
        if (userIsNotAdministrator()) {
            return Response.status(404).build();
        }

        Settings settings = settingsService.getSettings();
        return Response.ok(settings.getMaskedValues()).build();
    }

    @PUT
    public Response setSettings(Map<String, String> newSettings) {
        if (userIsNotAdministrator()) {
            return Response.status(404).build();
        }

        Settings settings = settingsService.getSettings();
        settings.setValues((newSettings));
        return Response.ok(settings.getMaskedValues()).build();
    }

    @GET
    @Path("{configKey}")
    public Response getSettings(@PathParam("configKey") String configKey) {
        if (userIsNotAdministrator()) {
            return Response.status(404).build();
        }

        Settings settings = settingsService.getSettings(configKey);

        if (settings == null) {
            return Response.status(404).build();
        }

        return Response.ok(settings.getMaskedValues()).build();
    }

    @PUT
    @Path("{configKey}")
    public Response setSettings(@PathParam("configKey") String configKey, Map<String, String> newSettings) {
        if (userIsNotAdministrator()) {
            return Response.status(404).build();
        }

        Settings settings = settingsService.getSettings(configKey);

        if (settings == null) {
            return Response.status(404).build();
        }

        settings.setValues((newSettings));
        return Response.ok(settings.getMaskedValues()).build();
    }

    private boolean userIsNotAdministrator() {
        ConfluenceUser user = AuthenticatedUserThreadLocal.get();
        return user == null
                || !permissionManager.hasPermission(user, Permission.ADMINISTER, PermissionManager.TARGET_SYSTEM);
    }

    public void setPermissionManager(PermissionManager permissionManager) {
        this.permissionManager = permissionManager;
    }

    public void setSettingsService(SettingsService settingsService) {
        this.settingsService = settingsService;
    }
}
