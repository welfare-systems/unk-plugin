package unknownvazguz13.unkplugin.eventos;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class Chat implements Listener {

	@EventHandler
	public void modificarChat(AsyncPlayerChatEvent event) {
		Player jugador = event.getPlayer();
		String message = event.getMessage();
		String[] bloqueadas = {"hacker", "noob", "ez"};
		for(int i=0;i<bloqueadas.length;i++) {
			
			
			if(message.toLowerCase().contains(bloqueadas[i])) {
				jugador.sendMessage(ChatColor.GREEN + "[UnkPlugin] " + ChatColor.RED + "Utiliza un lenguaje apropiado porfavor.");
				jugador.playSound(jugador.getLocation(), Sound.BLOCK_NOTE_PLING, 10, (float) 0.2);
				event.setCancelled(true);
				return;
				/*
				String a ="";
				for(int c=0;c<bloqueadas[i].length();c++) {
					a = a+"";
				}
				message = message.replace(bloqueadas[i], a);
				*/
			}
		}
		event.setMessage(message);
		
		
		
		
	}
	
}
