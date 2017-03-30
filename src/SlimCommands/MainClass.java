package SlimCommands;

import SlimCommands.Commands.AddCommand;
import SlimCommands.Commands.DeleteCommand;
import SlimCommands.Commands.ListCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MainClass extends JavaPlugin {

    public void onEnable() {
        registerEvents();
        registerCommands();
    }

    public void onDisable() {

    }

    private void registerCommands() {
        getCommand("addcommand").setExecutor(new AddCommand());
        getCommand("delcommand").setExecutor(new DeleteCommand());
        getCommand("listcommands").setExecutor(new ListCommand());
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new CommandEvent(), this);
    }

}
