package de.gamelos.hacklos;

import java.util.ArrayList;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import net.md_5.bungee.api.ChatColor;

public class AntiNoSlowdown implements Listener {

	static ArrayList<Player> bowspanned = new ArrayList<>();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if(!bowspanned.contains(p)){
			return;
		}
		if(!p.isOnGround()){
			return;
		}
		
		Location from = e.getFrom();
		Location to = e.getTo();
		if(e.getFrom().getY() != e.getTo().getY()){
			return;
		}
		Vector vec = to.toVector();
		double dis = vec.distance(from.toVector());
		if(dis > 0.15D){
//			e.setCancelled(true);
			p.sendMessage(ChatColor.GOLD+"[Hacklos]"+ChatColor.RED+" Bitte deaktiviere NoSlowdown");
//			p.teleport(from);
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(p.getItemInHand() != null){
			if(p.getItemInHand().getType().equals(Material.BOW)){
				if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
					if(p.getInventory().contains(Material.ARROW)){
						bowspanned.add(p);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onsho(EntityShootBowEvent e){
		if(e.getEntity() instanceof Player){
			Player p = (Player)e.getEntity();
			bowspanned.remove(p);
		}
	}
	
	@EventHandler
	public void oSwitch(PlayerItemHeldEvent e){
		Player p = e.getPlayer();
		if(bowspanned.contains(p)){
			bowspanned.remove(p);
		}
	}
	
}
