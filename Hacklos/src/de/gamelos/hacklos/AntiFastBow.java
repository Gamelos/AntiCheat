package de.gamelos.hacklos;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

public class AntiFastBow implements Listener {

	 @EventHandler
	 public void onbow(EntityShootBowEvent e){
		 if(e.getEntity() instanceof Player){
			 Player p = (Player) e.getEntity();
			 if(Main.bowshots.containsKey(p)){
				 Main.bowshots.replace(p, (Main.bowshots.get(p)+1));
			 }else{
				 Main.bowshots.put(p, 1);
			 }
			 
			 if(Main.bowshots.get(p) > 4){
//					====================================
					if(Main.hackerdetect.containsKey(p)){
						Main.hackerdetect.put(p, Main.hackerdetect.get(p)+2.0);
					}else{
						Main.hackerdetect.put(p, 2.0);
					}
//					p.sendMessage("FastBow");
					if(!dec.contains(p)){
						dec.add(p);
					}else{
						Main.FastBow.add(p);
					}
					Main.sendhackingmessageifhacking(p);
//					===================================
			 }else{
				 if(dec.contains(p)){
					 dec.remove(p);
				 }
			 }
			 
		 }
	 }
	
	 public static ArrayList<Player> dec = new ArrayList<>();
	 
}
