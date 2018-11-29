package de.gamelos.hacklos;

import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class AntiFastPlace implements Listener {

	public static HashMap<Player, Integer>place = new HashMap<>();
	
	@EventHandler
	public void onplace(BlockPlaceEvent e){
		Player p = e.getPlayer();
		if(place.containsKey(p)){
			int i = place.get(p);
			i++;
			place.remove(p);
			place.put(p, i);
		}else{
			place.put(p, 1);
		}
	
		
		if(place.get(p)>= 7){
			if(!dec.contains(p)){
				dec.add(p);
			}else{
				Main.FastPlace.add(p);
			}
//			e.setCancelled(true);
//			Bukkit.broadcastMessage("fastplace");
		}else{
			if(dec.contains(p)){
				dec.remove(p);
			}
		}
	}
	
	public static ArrayList<Player>dec = new ArrayList<>();
	
}
