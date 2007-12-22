package games.stendhal.server.core.rp.guilds;

import games.stendhal.server.core.engine.StendhalRPWorld;
import games.stendhal.server.core.engine.StendhalRPZone;
import java.util.LinkedList;
import java.util.List;
import marauroa.common.game.RPObject;

/**
 * Loads a list of permissions/ranks for a guild from the guilds zone.
 * @author timothyb89
 */
public class GuildPermissionList {
    
    public static final String GUILD_ZONE = GuildList.GUILD_ZONE;
    
    private static GuildPermissionList instance;
    
    private StendhalRPZone zone;
    private List<GuildPermission> perms;
    
    private GuildPermissionList() {
        loadZone();
        loadPermissions();
    }
    
    public static GuildPermissionList get() {
        if (instance == null) {
            instance = new GuildPermissionList();
        }
        return instance;
    }
    
    private void loadZone() {
        zone = StendhalRPWorld.get().getZone(GUILD_ZONE);
    }
    
    private void loadPermissions() {
        perms = new LinkedList<GuildPermission>();
        for (RPObject o : zone) {
            if (o instanceof GuildPermission) {
                perms.add((GuildPermission) o);
            }
        }
    }
    
    public void addPermission(GuildPermission g) {
        zone.add(g);
        zone.storeToDatabase();
    }
    
    public void removePermission(GuildPermission p) {
        zone.remove(p);
        zone.storeToDatabase();
    }
    
    public void removePermission(String identifier) {
        for (GuildPermission g : perms) {
            if (g.getIdentifier().equals(identifier)) {
                zone.remove(g);
                break;
            }
        }
        zone.storeToDatabase();
    }
    
    public List<GuildPermission> getPermissionsForGuild(Guild guild) {
        List<GuildPermission> gps = new LinkedList<GuildPermission>();
        for (GuildPermission g : perms) {
            if (g.getGuild().equals(guild.getIdentifier())) {
                gps.add(g);
            }
        }
        return gps;
    }
    
}
