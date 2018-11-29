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
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class AntiSpeed implements Listener {

	@EventHandler
	public void onMove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		Location to = e.getTo();
		Location from = e.getFrom();
		
		Vector vec = to.toVector().setY(0.0D);
		double i = vec.distance(from.toVector().setY(0.0D));
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
		
//		0.1 max
		Double x = from.getY() - to.getY();
		if(x > 0.1 || x< -0.3){
			return;
		}
		
		if(p.getLocation().add(0, 2, 0).getBlock().getType() != Material.AIR){
			return;
		}
		
		if(HitListener.hitplayer.contains(p)){
			return;
		}
		
		if(p.hasPotionEffect(PotionEffectType.SPEED)){
			if(i >= 0.454D){
//				p.sendMessage("speed");
//				e.setCancelled(true);
//				p.teleport(e.getFrom());
//				====================================
//				p.sendMessage("speed");
				if(!dec.contains(p)){
					dec.add(p);
				}else{
					Main.Speed.add(p);
				}
				if(Main.hackerdetect.containsKey(p)){
					Main.hackerdetect.put(p, Main.hackerdetect.get(p)+1.0);
				}else{
					Main.hackerdetect.put(p, 0.5);
				}
				Main.sendhackingmessageifhacking(p);
//				===================================
			}else{
				if(dec.contains(p)){
					dec.remove(p);
				}
			}
			
		}else{
			if(i >= 0.457D){
//				e.setCancelled(true);
//				p.teleport(e.getFrom());
				if(!dec.contains(p)){
					dec.add(p);
				}else{
					Main.Speed.add(p);
				}
//				====================================
				if(Main.hackerdetect.containsKey(p)){
					Main.hackerdetect.put(p, Main.hackerdetect.get(p)+1.0);
				}else{
					Main.hackerdetect.put(p, 0.5);
				}
				Main.sendhackingmessageifhacking(p);
//				===================================
			}else{
				if(dec.contains(p)){
					dec.remove(p);
				}
			}
		}
		
	}
	public static ArrayList<Player>dec = new ArrayList<>();
}
