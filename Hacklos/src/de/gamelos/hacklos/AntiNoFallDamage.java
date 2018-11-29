package de.gamelos.hacklos;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class AntiNoFallDamage implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onmove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		Location from = e.getFrom().clone();
		Location to = e.getTo().clone();
		
		Vector vec = to.toVector();
		double i = vec.distance(from.toVector());
		
		if(i == 00D){
			return;
		}
		
		if(p.getGameMode().equals(GameMode.CREATIVE)){
			return;
		}
		
		if(p.getVehicle() != null){
			return;
		}
		
		if(from.getY() < to.getY()){
			return;
		}
		
		if((p.getFallDistance() == 0.0F) &&
		(i > 0.79D) && (p.isOnGround())){
//			e.setCancelled(true);
			if(!dec.contains(p)){
				dec.add(p);
			}else{
				Main.NoFall.add(p);
			}
//			p.sendMessage("nofall");
//			====================================
			if(Main.hackerdetect.containsKey(p)){
				Main.hackerdetect.put(p, Main.hackerdetect.get(p)+1.0);
			}else{
				Main.hackerdetect.put(p, 1.0);
			}
			Main.sendhackingmessageifhacking(p);
//			===================================
		}else{
			if(dec.contains(p)){
				dec.remove(p);
			}
		}
		
	}
	public static ArrayList<Player>dec = new ArrayList<>();
}
