package games.stendhal.server.core.rp.guilds;

import games.stendhal.server.entity.Entity;
import java.util.LinkedList;
import java.util.List;
import marauroa.common.game.Definition;
import marauroa.common.game.Definition.Type;
import marauroa.common.game.RPClass;
import marauroa.common.game.RPObject;

/**
 * Represents a specific Guild.
 * This can have a name, a path to a URL where an image is located, etc.
 * @author timothyb89
 */
public class Guild extends Entity {
    
    /**
     * The guild name.
     */
    private String name;
    
    /**
     * The guild slogan.
     */
    private String slogan;
    
    /**
     * A url to the path of an image or logo representing the guild.
     */
    private String imageURL;
    
    /**
     * The members of the guild.
     */
    private List<GuildMember> members;
    
    /**
     * All of the permissions the guild has.
     */
    private List<GuildPermission> permissions;
    
    /**
     * The GuildPermission for admins. 
     * Admins and normal classes are required.
     */
    private GuildPermission adminRank;
    
    /**
     * The GuildPermission for normal users.
     * This is required to create a guild.
     */
    private GuildPermission normalRank;
    
    /**
     * The identifier of the guild. This has no spaces, special characters, etc.
     * This is used to link GuildMembers to Guilds, etc.
     */
    private String identifier;
    
    private static final String RPCLASS = "guild";
    private static final String ATTR_NAME = "name";
    private static final String ATTR_SLOGAN = "slogan";
    private static final String ATTR_IMAGEURL = "image";
    private static final String ATTR_ADMINRANK = "adminrank";
    private static final String ATTR_NORMALRANK = "normalrank";
    private static final String ATTR_IDENTIFIER = "identifier";

    public Guild(String name, String slogan, String imageURL, 
            GuildPermission adminRank, GuildPermission normalRank, 
            String identifier) {
        this.name = name;
        this.slogan = slogan;
        this.imageURL = imageURL;
        this.adminRank = adminRank;
        this.normalRank = normalRank;
        
        members = new LinkedList<GuildMember>();
        permissions = new LinkedList<GuildPermission>();
        
        setRPClass(RPCLASS);
        store();
        put(ATTR_NAME, name);
        put(ATTR_SLOGAN, slogan);
        put(ATTR_IMAGEURL, imageURL);
        put(ATTR_ADMINRANK, adminRank.getIdentifier());
        put(ATTR_NORMALRANK, normalRank.getIdentifier());
        put(ATTR_IDENTIFIER, identifier);
    }
    
    public Guild(RPObject obj) {
        super(obj);
        store();
        loadData();
    }

    public static void generateRPClass() {
        RPClass clazz = new RPClass(RPCLASS);
        clazz.isA("entity");
        clazz.addAttribute(ATTR_NAME, Type.STRING, Definition.HIDDEN);
        clazz.addAttribute(ATTR_SLOGAN, Type.LONG_STRING, Definition.HIDDEN);
        clazz.addAttribute(ATTR_IMAGEURL, Type.STRING, Definition.HIDDEN);
        clazz.addAttribute(ATTR_ADMINRANK, Type.STRING, Definition.HIDDEN);
        clazz.addAttribute(ATTR_NORMALRANK, Type.STRING, Definition.HIDDEN);
        clazz.addAttribute(ATTR_IDENTIFIER, Type.STRING, Definition.HIDDEN);
    }
    
    private void loadData() {
        permissions = GuildPermissionList.get().getPermissionsForGuild(this);
        name = get(ATTR_NAME);
        slogan = get(ATTR_SLOGAN);
        imageURL = get(ATTR_IMAGEURL);
        adminRank = getRank(get(ATTR_ADMINRANK));
        normalRank = getRank(get(ATTR_NORMALRANK));
        identifier = get(ATTR_IDENTIFIER);
    }
    
    public GuildPermission getAdminRank() {
        return adminRank;
    }

    public void setAdminRank(GuildPermission adminRank) {
        this.adminRank = adminRank;
        put(ATTR_ADMINRANK, adminRank.getIdentifier());
    }

    public String getIdentifier() {
        return identifier;
    }
    
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
        put(ATTR_IDENTIFIER, identifier);
    }
    
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
        put(ATTR_IMAGEURL, imageURL);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        put(ATTR_NAME, name);
    }

    public GuildPermission getNormalRank() {
        return normalRank;
    }

    public void setNormalRank(GuildPermission normalRank) {
        this.normalRank = normalRank;
        put(ATTR_NORMALRANK, normalRank.getIdentifier());
    }
    
    public List<GuildPermission> getPermissions() {
        return permissions;
    }
    
    public GuildPermission getPermission(String identifier) {
        for (GuildPermission gp : getPermissions()) {
            if (gp.getIdentifier().equals(identifier)) {
                return gp;
            }
        }
        return null;
    }
    
    public GuildPermission getPermission(int rank) {
        return GuildPermission.getPermission(rank, getPermissions());
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
        put(ATTR_SLOGAN, slogan);
    }
    
    public boolean isAdmin(GuildMember member) {
        int memberRank = member.getPermission().getRank();
        if (memberRank == getAdminRank().getRank()) {
            return true;
        } else {
            return false;
        }
    }
    
    public GuildPermission getRank(String identifier) {
        for (GuildPermission g : permissions) {
            if (g.getIdentifier().equals(name)) {
                return g;
            }
        }
        return null;
    }
    
}
