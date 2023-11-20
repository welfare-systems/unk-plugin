package unknownvazguz13.unkplugin;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;
import unknownvazguz13.unkplugin.comandos.ComandoPrincipal;
import unknownvazguz13.unkplugin.comandos.ComandoYoutube;
import unknownvazguz13.unkplugin.eventos.Chat;
import unknownvazguz13.unkplugin.eventos.Entrar;
import unknownvazguz13.unkplugin.eventos.ScoreboardAdmin;

public class Unkplugin extends JavaPlugin {
	public String rutaConfig;
	PluginDescriptionFile pdffile = getDescription(); 
	private static Economy econ = null;
	
	public String version = pdffile.getVersion();
	public String nombre = pdffile.getName();
	
	public void onEnable(){
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"<------------------------------->");
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[UnkPlugin]"+ ChatColor.GREEN+" activado Version"+ ChatColor.YELLOW+   version);
		if(setupEconomy()) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[UnkPlugin]"+ ChatColor.GREEN+" Dependencia: Vault ha sido activada");
		}else {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[UnkPlugin]"+ ChatColor.GREEN+" Dependencia: Vault no ha sido encontrada...");
		}
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"<------------------------------->");
		registrarComandos();
		registerEvents();
		registerConfig();
		
		ScoreboardAdmin scoreboard = new ScoreboardAdmin(this);
		scoreboard.crearScoreboard(Integer.valueOf(getConfig().getString("Scoreboard.ticks")));
		
		}
	
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN +nombre+ ChatColor.RED +" Ha sido desactivado");
	}
	
	private boolean setupEconomy() {
		if(getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if(rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}
	
	public Economy getEconomy() {
		return this.econ;
	}
	
	public void registrarComandos() {
		this.getCommand("youtube").setExecutor(new ComandoYoutube(this));
		this.getCommand("unkplugin").setExecutor(new ComandoPrincipal(this));
	}
	
	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new Entrar(this), (this));
		pm.registerEvents(new Chat(), (this));
	}
	
	public void registerConfig() {
			File config = new File(this.getDataFolder(),"config.yml");
			rutaConfig = config.getPath();
			if(!config.exists()){
				this.getConfig().options().copyDefaults(true);
				saveConfig();
		}
	}
	
}
