package de.gamelos.hacklos;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

public class HitListener implements Listener {

	public static ArrayList<Player> hitplayer = new ArrayList<>();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onHit(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Player){
		Player p = (Player) e.getEntity();
		
		if(!hitplayer.contains(p)){
			hitplayer.add(p);
			Plugin pl = Bukkit.getPluginManager().getPlugin("Hacklos");
			Bukkit.getScheduler().scheduleAsyncDelayedTask(pl, new Runnable(){
				@Override
				public void run() {
					hitplayer.remove(p);
				}
			}, 20);
		}
		}
	}
	
	
}
