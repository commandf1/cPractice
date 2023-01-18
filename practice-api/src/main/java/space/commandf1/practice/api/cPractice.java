package space.commandf1.practice.api;

import org.bukkit.Bukkit;
import space.commandf1.practice.api.exception.APINotEnableException;

/**
 * The api instance interface
 * @since 1.0-SNAPSHOT
 * @author commandf1
 * */
@SuppressWarnings("unused")
public interface cPractice {

    /**
     * Get API instance
     * @since 1.0-SNAPSHOT
     * @author commandf1
     * */
    static cPractice getAPI() {
        cPractice api = Bukkit.getServicesManager().getRegistration(cPractice.class).getProvider();
        if (!api.isEnabled()) {
            throw new APINotEnableException();
        }
        return api;
    }

    /**
    * Get if the api be enabled;
    * @since 1.0-SNAPSHOT
    * @author commandf1
    * */
    boolean isEnabled();

    /**
     * Get the version of the plugin
     * @since 1.0-SNAPSHOT
     * @author commandf1
     * */
    String getVersion();
}
