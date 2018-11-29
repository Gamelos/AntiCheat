package de.gamelos.hacklos;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class AntiSceffaultWalk implements Listener {

	@EventHandler
	public void onplayce(BlockPlaceEvent e){
		 Player p = e.getPlayer();
		 if(p.getGameMode()== GameMode.CREATIVE){
			 return;
		 }
		 if(Main.anitscaffult.containsKey(p)){
			 Main.anitscaffult.replace(p, (Main.anitscaffult.get(p)+1));
		 }else{
			 Main.anitscaffult.put(p, 1);
		 }
		 
		 
		 if(Main.anitscaffult.get(p) > 4){
			 if(p.getFallDistance() == 0.0D){
			if(e.getBlock().getLocation().add(0, -1, 0).getBlock().getType() == Material.AIR){
//				====================================
				if(Main.hackerdetect.containsKey(p)){
					Main.hackerdetect.put(p, Main.hackerdetect.get(p)+1.0);
				}else{
					Main.hackerdetect.put(p, 1.0);
				}
				Main.sendhackingmessageifhacking(p);
				if(!dec.contains(p)){
					dec.add(p);
				}else{
					Main.Sceffault.add(p);
				}
//				p.sendMessage("ScaffaultWalk");
//				===================================
//			 e.setCancelled(true);
			}
			 }
		 }else{
				if(dec.contains(p)){
					dec.remove(p);
				}
			}
		 
	}
	
	public static ArrayList<Player>dec = new ArrayList<>();
//	-0.135
//	-0.185
	
}