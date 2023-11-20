package unknownvazguz13.unkplugin.eventos;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import unknownvazguz13.unkplugin.Unkplugin;

public class Entrar implements Listener{
	
	private Unkplugin plugin;
	
	public Entrar(Unkplugin plugin) {
			this.plugin = plugin;
		}



	@EventHandler
	public void alEntrar(PlayerJoinEvent event) {
		Player jugador = event.getPlayer();
		FileConfiguration config = plugin.getConfig();
		
		
		if(config.contains("Config.Spawn.x")) {
			double x = Double.valueOf(config.getString("Config.Spawn.x"));
			double y = Double.valueOf(config.getString("Config.Spawn.y"));
			double z = Double.valueOf(config.getString("Config.Spawn.z"));
			float yaw = Float.valueOf(config.getString("Config.Spawn.yaw"));
			float pitch = Float.valueOf(config.getString("Config.Spawn.pitch"));
			World world = plugin.getServer().getWorld(config.getString("Config.Spawn.world"));
			Location l = new Location(world, x, y, z, yaw, pitch); //-157 64 146 -2 -9
			jugador.teleport(l);
	}
		
		
		String path = "Config.mensaje-bienvenida";
		if(config.getString(path).equals("true")) {
			String texto = "Config.mensaje-bienvenida-texto";
			jugador.sendMessage(config.getString(texto));
		}
		return ;
   }
}
