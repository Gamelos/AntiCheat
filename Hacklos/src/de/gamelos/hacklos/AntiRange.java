package de.gamelos.hacklos;
import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class AntiRange implements Listener {

	@EventHandler
	public void onhit(EntityDamageByEntityEvent e){
		Entity p1 = e.getEntity();
		Entity hitter1 = e.getDamager();
		
		if(p1 instanceof Player && hitter1 instanceof Player){
			
			
			Player p = (Player) e.getEntity();
			Player hitter = (Player) e.getDamager();
			
			Location ploc = p.getLocation();
			Location hitterloc = hitter.getLocation();
			
			double distance = ploc.distance(hitterloc);
			double maxrange = Main.loc.getDouble("Settings.AntiRange.MaxRange");
			
			if(distance >= maxrange){
				if(!dec.contains(p)){
					dec.add(p);
				}else{
					Main.Range.add(p);
				}
//				e.setCancelled(true);
//				====================================
				if(Main.hackerdetect.containsKey(p)){
					Main.hackerdetect.put(p, Main.hackerdetect.get(p)+1.0);
				}else{
					Main.hackerdetect.put(p, 1.0);
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
