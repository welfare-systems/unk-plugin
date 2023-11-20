package unknownvazguz13.unkplugin.comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.milkbowl.vault.economy.Economy;
import unknownvazguz13.unkplugin.Unkplugin;


	public class ComandoPrincipal implements CommandExecutor {
	
		private Unkplugin plugin;
		
	public ComandoPrincipal(Unkplugin plugin) {
			this.plugin = plugin;
		}

	public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) {

	if(!(sender instanceof Player)) {
		Bukkit.getConsoleSender().sendMessage(plugin.nombre + "No puedes ejecutar un comando desde la consola");
		return false;
	} else {
		Player jugador = (Player) sender;
		if(args.length > 0) {
			if(args[0].equalsIgnoreCase("version")) {
				jugador.sendMessage(ChatColor.RED+ plugin.nombre+ ChatColor.GREEN+" La versi�n del Plugin es:" + plugin.version);
				return true;
			}
			
			else if(args[0].equalsIgnoreCase("spawn")) {
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
					return true;
				}else{
					jugador.sendMessage(ChatColor.GREEN + plugin.nombre + ChatColor.RED +" [ERROR] El spawn no ha sido establecido.");
						return true;
				}
		}
			
			
			
			else if(args[0].equalsIgnoreCase("anunciar")) {
				if(args.length == 1) {
					jugador.sendMessage(ChatColor.GREEN + " [UnkPlugin] "+ ChatColor.RED + " [X ERROR]"+ ChatColor.YELLOW + " Carencia de argumentos, uso correcto del comando: /unkplugin anunciar <Texto>" );
				}else {
					Economy econ = plugin.getEconomy();
					double dinero = econ.getBalance(jugador);
					if(dinero >= 1000) {
						econ.withdrawPlayer(jugador, 1000);
						String mensaje = "";
						for(int i=1;i<=args.length-1;i++) {
							mensaje = mensaje+args[i]+" ";
						}
						
						jugador.sendMessage(ChatColor.GREEN + " [UnkPlugin] "+ ChatColor.RED + " [Has usado $1000 pesos]");
						plugin.getServer().broadcastMessage(ChatColor.GREEN + "[Anuncio de:" + ChatColor.RED +  jugador.getName() + ChatColor.GREEN+"]" +  mensaje);
						 return true;
					}else {
						jugador.sendMessage(ChatColor.GREEN + " [UnkPlugin] "+ ChatColor.RED + " [X ERROR]"+ ChatColor.YELLOW + " No tienes el dinero suficiente para ejecutar este comando, dinero necesario: $1000" );
					return true;
					}
				}
			}
			
			else if(args[0].equalsIgnoreCase("reload")) {
				plugin.reloadConfig();
				jugador.sendMessage(ChatColor.GREEN+ plugin.nombre+ ChatColor.RED +" Ha sido recargado exitosamente" );
			return true;
			
		}
			
			else if(args[0].equalsIgnoreCase("fly")) {
				if(jugador.hasPermission("customer.fly")) {
					if(jugador.isFlying()) {
						jugador.setAllowFlight(false);
						jugador.setFlying(false);
						jugador.sendMessage(ChatColor.GREEN + " [UnkPlugin]" + ChatColor.RED + " El comando fly ha sido desactivado");
						return true;
					} else {
						jugador.setAllowFlight(true);
						jugador.setFlying(true);
						jugador.sendMessage(ChatColor.RED + " [UnkPlugin]" + ChatColor.GREEN + " EL comando fly se ha habilitado");
					}
				} else {
					jugador.sendMessage(ChatColor.GREEN + " [UnkPlugin]" + ChatColor.RED + " [X ERROR]" + ChatColor.YELLOW + " No tienes permisos!");
				}
			}
			
			else if(args[0].equalsIgnoreCase("feed")) {
				if(jugador.hasPermission("unk.feed")) {
					@SuppressWarnings("unused")
					
					int maxFoodLevel = 20;
					
					if(jugador.getFoodLevel() < maxFoodLevel) {
						jugador.setFoodLevel(maxFoodLevel);
						jugador.sendMessage(ChatColor.GREEN + " [UnkPlugin]" + ChatColor.RED + " Tu nivel de comida, ha sido restablecido al 100%!");
						return true;
					}else {
						jugador.sendMessage(ChatColor.GREEN + " [UnkPlugin]" + ChatColor.RED + " [X ERROR]" + ChatColor.YELLOW + " Tu nivel de comida ya esta al 100%!");
					}
				}else{
					jugador.sendMessage(ChatColor.GREEN + " [UnkPlugin]" + ChatColor.RED + " [X ERROR]" + ChatColor.YELLOW + " No tienes los permisos necesarios o suficientes!");
				}
			}
			
			
			
			else if(args[0].equalsIgnoreCase("setspawn")) {
				Location l = jugador.getLocation();
				double x = l.getX();
				double y = l.getY();
				double z = l.getZ();
				String world = l.getWorld().getName();
				float yaw = l.getYaw();
				float pitch = l.getPitch();
				FileConfiguration config = plugin.getConfig();
				config.set("Config.Spawn.x", x);
				config.set("Config.Spawn.y", y);
				config.set("Config.Spawn.z", z);
				config.set("Config.Spawn.world", world);
				config.set("Config.Spawn.yaw", yaw);
				config.set("Config.Spawn.pitch", pitch);
				plugin.saveConfig();
				jugador.sendMessage(ChatColor.GREEN +" El spawn del servidor se ha establecido exitosamente.");

			return true;
			
			}else{
				jugador.sendMessage(ChatColor.RED+ plugin.nombre+ ChatColor.RED+" X [ERROR]"+ ChatColor.YELLOW+" Ese comando no existe!"+ ChatColor.GREEN+" Usa: /unkplugin version para ver la version del plugin." );
				return true;
			}
			
			
		}else {
			jugador.sendMessage(ChatColor.GREEN+ plugin.nombre+ ChatColor.RED+" X [ERROR]"+ ChatColor.YELLOW+" Usa: /unkplugin version para ver la version del plugin.");
			return true;
	     }
	  }
	return false;
	
   }

}
	
	