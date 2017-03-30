package SlimCommands.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class ListCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("pCommands.editCommand") || player.isOp()) {
                File file = new File(Bukkit.getServer().getPluginManager().getPlugin("SlimCommands").getDataFolder() + "/Custom Commands.yml");
                YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
                player.sendMessage(ChatColor.AQUA + "List of Custom Commands:");
                for (String key : myFile.getConfigurationSection("Commands").getKeys(false)) {
                    player.sendMessage(ChatColor.GOLD + "   " + key + ": " + myFile.getString(new StringBuilder().append("Commands.").append(key).toString()));
                }
                return true;
            }
        }
        return false;
    }
}
