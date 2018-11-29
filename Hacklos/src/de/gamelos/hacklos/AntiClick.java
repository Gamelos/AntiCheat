package de.gamelos.hacklos;

import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class AntiClick implements Listener {
	
	public static HashMap<Player,Integer>cps = new HashMap<>();
	
	@EventHandler
	public void klick(PlayerInteractEvent e){
		if(e.getAction() == Action.LEFT_CLICK_AIR){
			Player p = e.getPlayer();
			
			if(cps.containsKey(p)){
				int i = cps.get(p);
				i++;
				cps.remove(p);
				cps.put(p, i);
			}else{
				cps.put(p, 1);
			}
			
			if(maxclicks.containsKey(p)){
				if(maxclicks.get(p)<cps.get(p)){
					maxclicks.put(p, cps.get(p));
				}
			}else{
				maxclicks.put(p, 0);
			}
			
			
			if(cps.get(p) >= 14){
				if(!info.contains(p)){
//				e.getPlayer().sendMessage("zu groﬂer hitspeed ("+cps.get(p)+")");
				Main.Clickspeed.add(p);
				info.add(p);
//				====================================
				if(Main.hackerdetect.containsKey(p)){
					Main.hackerdetect.put(p, Main.hackerdetect.get(p)+1.0);
				}else{
					Main.hackerdetect.put(p, 1.0);
				}
				Main.sendhackingmessageifhacking(p);
				}
//				===================================
			}else{
				if(dec.contains(p)){
					dec.remove(p);
				}
			}
			
		}
	}
	
	public static ArrayList<Player>dec = new ArrayList<>();
	public static ArrayList<Player> info = new ArrayList<>();
	public static HashMap<Player,Integer>maxclicks = new HashMap<>();
	
	}
