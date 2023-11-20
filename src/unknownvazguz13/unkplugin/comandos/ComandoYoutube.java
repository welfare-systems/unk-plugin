package unknownvazguz13.unkplugin.comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import unknownvazguz13.unkplugin.Unkplugin;


	public class ComandoYoutube implements CommandExecutor {
	
		private Unkplugin plugin;
		
	public ComandoYoutube(Unkplugin plugin) {
			this.plugin = plugin;
		}

	public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) {

	if(!(sender instanceof Player)) {
		Bukkit.getConsoleSender().sendMessage(plugin.nombre + "No puedes ejecutar un comando desde la consola");
		return false;
	}else {
		Player jugador = (Player) sender;
		jugador.sendMessage(ChatColor.RED + "-------------------------------------------------");
		jugador.sendMessage(ChatColor.GREEN + "Visita nuestro canal de youtube UnknownVazguz13");
		jugador.sendMessage(ChatColor.RED + "-------------------------------------------------");

		
		return true;
	}
	
	}

}