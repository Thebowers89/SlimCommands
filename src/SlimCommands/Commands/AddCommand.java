package SlimCommands.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class AddCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("pCommands.editCommand") || player.isOp()) {
                File file = new File(Bukkit.getServer().getPluginManager().getPlugin("SlimCommands").getDataFolder() + "/Custom Commands.yml");
                YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
                if (args.length >= 2) {
                    String string = "";
                    for (int i = 1; i < args.length; i++) {
                        if (i == args.length - 1) {
                            String arg = args[i];
                            string = string + arg;
                        } else {
                            String arg = args[i] + " ";
                            string = string + arg;
                        }
                    }
                    myFile.set("Commands." + args[0], string);
                    try {
                        myFile.save(file);
                        player.sendMessage("Successfully saved command: " + args[0]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                player.sendMessage("Invalid Syntax!");
                return true;
            }
        }
        return false;
    }
}
