package SlimCommands.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class DeleteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("pCommands.editCommand") || player.isOp()) {
                File file = new File(Bukkit.getServer().getPluginManager().getPlugin("SlimCommands").getDataFolder() + "/Custom Commands.yml");
                YamlConfiguration myFile = YamlConfiguration.loadConfiguration(file);
                if (args.length == 1) {
                    if (myFile.contains("Commands." + args[0])) {
                        myFile.set("Commands." + args[0], null);
                        try {
                            myFile.save(file);
                            player.sendMessage("Successfully deleted command: " + args[0]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return true;
                    }
                }
                player.sendMessage("Invalid Syntax!");
                return true;
            }
        }
        return false;
    }
}
