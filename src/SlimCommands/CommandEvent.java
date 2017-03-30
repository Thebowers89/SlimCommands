package SlimCommands;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.io.File;

public class CommandEvent implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("SlimCommands").getDataFolder() + "/Custom Commands.yml");
        YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
        Player player = e.getPlayer();
        String cmd = e.getMessage();
        if (myFile.contains("Commands")) {
            for (String key : myFile.getConfigurationSection("Commands").getKeys(false)) {
                if (cmd.equalsIgnoreCase("/" + key))
                {
                    String command = myFile.getString("Commands." + key);
                    e.setCancelled(true);
                    player.getServer().dispatchCommand(player, command);
                }
            }
        }
    }

}
