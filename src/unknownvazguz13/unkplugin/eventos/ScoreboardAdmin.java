package unknownvazguz13.unkplugin.eventos;


import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import unknownvazguz13.unkplugin.Unkplugin;

public class ScoreboardAdmin {
	private Unkplugin plugin;
	int taskID;
	public ScoreboardAdmin(Unkplugin plugin) {
			this.plugin = plugin;
		}
	
	
	public void crearScoreboard(int ticks) {
		BukkitScheduler schedule = Bukkit.getServer().getScheduler();
		taskID = schedule.scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				FileConfiguration config = plugin.getConfig();
				for(Player player : Bukkit.getOnlinePlayers()) {
					actualizarScoreboard(player, config);
				}
			}
			
			
		}, 0, ticks);
	}
	
	private void actualizarScoreboard(Player jugador, FileConfiguration config) {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard scoreboard = manager.getNewScoreboard();
		Objective objetivo = scoreboard.registerNewObjective("UnkPlugin", "dummy");
		objetivo.setDisplaySlot(DisplaySlot.SIDEBAR);
		objetivo.setDisplayName(config.getString("Scoreboard.title"));
		List<String> lineas = config.getStringList("Scoreboard.txt");
		
		for(int i=0;i<lineas.size();i++) {
			Score score = objetivo.getScore(lineas.get(i));
			score.setScore(lineas.size()-(i));
		}
		jugador.setScoreboard(scoreboard);
		
	}
	
	
}
