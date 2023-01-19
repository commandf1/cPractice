package space.commandf1.practice.settings;

import org.bukkit.Material;
import space.commandf1.capi.config.Config;
import space.commandf1.capi.factory.factories.ItemFactory;

public enum Settings {
    API_ENABLE(false, "api-enable", Setting.getSettings()), // settings
    CLEAN_WORLD(true, "clean-world", Setting.getSettings()),
    NO_JOIN_LEAVE_MESSAGE(true, "no-join-leave-message", Setting.getSettings()),

    UNRANKED_QUEUE(new ItemFactory(Material.DIAMOND_SWORD, 1).setDisplayName("§eUnranked Queue").build(), "unranked-queue", Setting.getItems()), // items
    RANKED_QUEUE(new ItemFactory(Material.IRON_SWORD, 1).setDisplayName("§6Ranked Queue").build(), "ranked-queue", Setting.getItems())
    ;

    private Object obj;
    private final String path;
    private final Config config;

    Settings(Object obj, String path, Config config) {
        this.obj = obj;
        this.path = path;
        this.config = config;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public static void init() {
        // NO STATIC BLOCK!!!!
        /*
        * static {}
        * */
        for (Settings value : values()) {
            value.setObj(value.getConfig().getConfig().get(value.getPath()));
        }
    }

    public String getPath() {
        return path;
    }

    public Config getConfig() {
        return config;
    }
}
