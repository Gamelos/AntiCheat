package de.gamelos.hacklos;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;


public class AntiFly implements Listener {

	@SuppressWarnings("deprecation")
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
		
		if((p.getFallDistance() == 0.0F) && 
		(p.getLocation().getBlock().getRelative(BlockFace.UP).getType() == Material.AIR)){
			if(i > 1.0){
				if(p.isOnGround()){
					return;
				}
				if(flytime.containsKey(p)){
					int i1 = flytime.get(p);
					i1++;
					flytime.remove(p);
					flytime.put(p, i1);
					if(i1>=15){
						if(isfly.contains(p)){
						isfly.remove(p);
						}
					}
				}else{
					flytime.put(p, 1);
				}
//				e.setCancelled(true);
//				p.teleport(e.getFrom());
//				====================================
				if(!hit.contains(p)){
//				p.sendMessage("fly");
				if(Main.hackerdetect.containsKey(p)){
					Main.hackerdetect.put(p, Main.hackerdetect.get(p)+1.0);
				}else{
					Main.hackerdetect.put(p, 1.0);
				}
				Main.sendhackingmessageifhacking(p);
				}
//				===================================
			}else{
				if(dec.containsKey(p)){
					dec.remove(p);
				}
			}
		}else if(p.isFlying()){
			if(p.isOnGround()){
				return;
			}
//			e.setCancelled(true);
//			p.teleport(e.getFrom());
//			====================================
			if(Main.hackerdetect.containsKey(p)){
				Main.hackerdetect.put(p, Main.hackerdetect.get(p)+1.0);
			}else{
				Main.hackerdetect.put(p, 1.0);
			}
			Main.sendhackingmessageifhacking(p);
//			===================================
		}
		
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void ond(EntityDamageEvent e){
		if(e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
			if(!hit.contains(hit.contains(p))){
				hit.add(p);
				Bukkit.getScheduler().scheduleAsyncDelayedTask(Bukkit.getPluginManager().getPlugin("Hacklos"), new Runnable(){
					@Override
					public void run() {
						hit.remove(p);
					}
				}, 40);
			}
		}
	}
	public static ArrayList<Player>hit = new ArrayList<>();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		startdetect(p);
	}
	
	@EventHandler
	public void onquit(PlayerQuitEvent e){
		Player p = e.getPlayer();
		if(scheduler.containsKey(p)){
			Bukkit.getScheduler().cancelTask(scheduler.get(p));
			scheduler.remove(p);
		}
	}
	
	public static HashMap<Player,Integer> scheduler = new HashMap<>();
	public static HashMap<Player,Location> lastloc = new HashMap<>();
	public static ArrayList<Player> detect = new ArrayList<>();
	
	@SuppressWarnings("deprecation")
	public static void startdetect(Player p){
		int count;
	Plugin pl = Bukkit.getPluginManager().getPlugin("Hacklos");
	count = Bukkit.getScheduler().scheduleAsyncRepeatingTask(pl, new Runnable(){
		@Override
		public void run() {
		
			Block b1 = p.getLocation().add(0, -1, 0).getBlock();
			Block b2 = p.getLocation().add(0, -2, 0).getBlock();
			if(b1.getType() == Material.AIR && b2.getType() == Material.AIR && p.getLocation().getBlock().getType() == Material.AIR){
				if(lastloc.containsKey(p)){
					lastloc.remove(p);
					lastloc.put(p, p.getLocation());
				}else{
					lastloc.put(p, p.getLocation());
				}
//				====================================
				if(flytime.containsKey(p)){
					int i = flytime.get(p);
					i++;
					flytime.remove(p);
					flytime.put(p, i);
					if(i>=15){
						if(isfly.contains(p)){
						isfly.remove(p);
						}
					}
				}else{
					flytime.put(p, 1);
				}
//				====================================
				
				if(p.getFallDistance() <= 4.0){
					if(detect.contains(p)){
						if(lastloc.containsKey(p)){
//						p.teleport(lastloc.get(p));
						}
						p.setFallDistance(0.0F);
						if(!p.getAllowFlight()&&!sneakon(p) && !isfly.contains(p) &&flytime.get(p)>1){
							if(!dec.containsKey(p)){
								dec.put(p,1);
							}else{
								int iii = dec.get(p);
								iii++;
								dec.put(p, iii);
								if(iii>3){
								Main.Fly.add(p);
								}
							}
//						p.sendMessage("fly2");
						}else{
							if(dec.containsKey(p)){
								dec.remove(p);
							}
						}
//						====================================
						if(Main.hackerdetect.containsKey(p)){
							Main.hackerdetect.put(p, Main.hackerdetect.get(p)+2.0);
						}else{
							Main.hackerdetect.put(p, 2.0);
						}
						Main.sendhackingmessageifhacking(p);
//						===================================
						detect.remove(p);
					}else {
							if(dec.containsKey(p)){
								dec.remove(p);
							}
						detect.add(p);
					}
			}
			}else{
				flytime.remove(p);
			}
			
		}
	}, 20, 20);
	scheduler.put(p, count);
	}
	
	
	public static ArrayList<Player> jump = new ArrayList<>();
	
	public static boolean sneakon(Player p){
		boolean b = false;
		
		if(p.getLocation().add(0, -1, 1).getBlock().getType() != Material.AIR){
			b = true;
		}
		if(p.getLocation().add(0, -1, -1).getBlock().getType() != Material.AIR){
			b = true;
		}
		if(p.getLocation().add(-1, -1, 0).getBlock().getType() != Material.AIR){
			b = true;
		}
		if(p.getLocation().add(1, -1, 0).getBlock().getType() != Material.AIR){
			
			b = true;
		}
		
		if(p.getLocation().add(1, -1, 1).getBlock().getType() != Material.AIR){
			b = true;
		}
		if(p.getLocation().add(1, -1, -1).getBlock().getType() != Material.AIR){
			b = true;
		}
		if(p.getLocation().add(-1, -1, -1).getBlock().getType() != Material.AIR){
			b = true;
		}
		if(p.getLocation().add(1, -1, -1).getBlock().getType() != Material.AIR){
			b = true;
		}
		return b;
	}
	
	public static ArrayList<Player> isfly = new ArrayList<>();
	public static HashMap<Player, Integer> flytime = new HashMap<>();
	public static HashMap<Player, Integer>dec = new HashMap<>();
	
}
