package de.gamelos.hacklos;

import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class AntiGroundSpeed implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onmo(PlayerMoveEvent e){
		
		Player p = e.getPlayer();
		if(e.getPlayer().getGameMode() != GameMode.CREATIVE){
		if(e.getPlayer().getLocation().add(0, 2, 0).getBlock().getType() == Material.AIR &&e.getPlayer().getLocation().add(0, -1, 0).getBlock().getType() != Material.STATIONARY_WATER&&e.getPlayer().getLocation().add(0, -1, 0).getBlock().getType() != Material.WATER&&e.getPlayer().getLocation().getBlock().getType() != Material.STATIONARY_WATER&&e.getPlayer().getLocation().getBlock().getType() != Material.WATER){
		if(e.getFrom().getY()<e.getTo().getY()){
		if(!list.contains(e.getPlayer())){
		if(p.getFallDistance() == 0.0){
		double i = e.getTo().getY()-e.getFrom().getY();
		if(i<=0.12){
			if(!p.getAllowFlight() && p.isOnGround()){
			if(!dec.containsKey(p)){
				dec.put(p, 1);
			}else{
				int iii = dec.get(p);
				iii++;
				dec.put(p, iii);
				if(iii>3){
				Main.GroundSpeed.add(p);
				}
			}
//		p.sendMessage("Groundspeed ("+i+")");
			}
		}else{
			if(dec.containsKey(p)){
				dec.remove(p);
			}
		}
		list.add(e.getPlayer());
		}
		}
		}else{
			if(list.contains(e.getPlayer())){
				list.remove(e.getPlayer());
			}
		}
		}
		}
	}
	
	public static ArrayList<Player> list = new ArrayList<>();
	public static HashMap<Player, Integer>dec = new HashMap<>();
}
