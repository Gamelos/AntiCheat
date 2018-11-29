package de.gamelos.hacklos;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class AntiWaterwalk implements Listener {

	@EventHandler
	public void onmove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		Location to = e.getTo();
		Location from = e.getFrom();
		
		Vector vec = to.toVector();
		double i = vec.distance(from.toVector());
		if(p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.SPONGE)){
			return;
		}
		if(p.getGameMode().equals(GameMode.CREATIVE)){
			return;
		}
		if(p.getEntityId() == 100){
		return;	
		}
			if(p.getVehicle() != null){
				return;
			}
			
		if(p.getAllowFlight() == true){
			return;
		} 
		if((i > 0.29D) && (i < 0.30)){
			if(p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.WATER)){
				return;
			}
			if(p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.LAVA)){
				return;
			}
			if(p.getLocation().getBlock().getRelative(BlockFace.DOWN).isLiquid()){
//				====================================
				if(Main.hackerdetect.containsKey(p)){
					Main.hackerdetect.put(p, Main.hackerdetect.get(p)+1.0);
				}else{
					Main.hackerdetect.put(p, 1.0);
				}
				if(!dec.contains(p)){
					dec.add(p);
				}else{
					Main.Waterwalk.add(p);
				}
//				p.sendMessage("WaterWalk");
				Main.sendhackingmessageifhacking(p);
//				===================================
			}
		}else{
			if(dec.contains(p)){
				dec.remove(p);
			}
		}
	}
	public static ArrayList<Player>dec = new ArrayList<>();
}
