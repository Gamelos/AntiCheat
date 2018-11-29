package de.gamelos.hacklos;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class AntiGlide implements Listener {

	@EventHandler
	public void onMove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		Double i = e.getTo().getY() - e.getFrom().getY();
		if(e.getPlayer().getGameMode() == GameMode.CREATIVE){
			return;
		}
		//-0.125
		if(i <= -0.008 && i >= -0.01 || i == -0.125){
//		if(e.getTo().subtract(0, 1, 0).getBlock().getType() == Material.AIR){
//			====================================
			if(!p.getAllowFlight()){
				if(!dec.contains(p)){
					dec.add(p);
				}else{
					Main.Glide.add(p);
				}
//			p.sendMessage("glide");
			if(Main.hackerdetect.containsKey(p)){
				Main.hackerdetect.put(p, Main.hackerdetect.get(p)+1.0);
			}else{
				Main.hackerdetect.put(p, 1.0);
			}
			Main.sendhackingmessageifhacking(p);
//			===================================
//			e.setCancelled(true);
			}else{
				if(dec.contains(p)){
					dec.remove(p);
				}
			}
//		}
	}else{
		if(dec.contains(p)){
			dec.remove(p);
		}
	}
	
	}
	public static ArrayList<Player>dec = new ArrayList<>();
}
