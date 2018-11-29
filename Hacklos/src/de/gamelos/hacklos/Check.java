package de.gamelos.hacklos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Check implements Listener {

	public static HashMap<Player, ItemStack> itemlist = new HashMap<>();
	
	@SuppressWarnings("deprecation")
	public static void autoarmor(Player p){
		ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta meta = item.getItemMeta();
		itemlist.put(p, p.getInventory().getItem(9));
		ArrayList<String> list = new ArrayList<>();
		list.add("19688o38903400398ﬂ287nmgfoiﬂ2784");
		meta.setLore(list);
		item.setItemMeta(meta);
		p.getInventory().setItem(9, item);
		Bukkit.getScheduler().scheduleAsyncDelayedTask(Bukkit.getPluginManager().getPlugin("Hacklos"), new Runnable(){
			@Override
			public void run() {
				p.getInventory().setItem(9, itemlist.get(p));
				itemlist.remove(p);
				if(ddd.contains(p)){
				dec.remove(p);
				ddd.remove(p);
				}
			}
		}, 5);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onin(InventoryClickEvent e){
		if(e.getCurrentItem()!=null){
			if(e.getCurrentItem().getType() == Material.DIAMOND_CHESTPLATE){
				if(e.getCurrentItem().getItemMeta().getLore() != null){
					List<String> list = e.getCurrentItem().getItemMeta().getLore();
					if(list.size()>0){
						if(list.get(0).equals("19688o38903400398ﬂ287nmgfoiﬂ2784")){
							e.setCancelled(true);
//							Main.AutoArmor.add((Player)e.getWhoClicked());
							Player p = (Player)e.getWhoClicked();
							if(dec.contains(p)){
								Main.AutoArmor.add((Player)e.getWhoClicked());
							}else{
								dec.add(p);
							Bukkit.getScheduler().scheduleAsyncDelayedTask(Bukkit.getPluginManager().getPlugin("Hacklos"), new Runnable(){
								@Override
								public void run() {
									ddd.add(p);
									autoarmor((Player)e.getWhoClicked());
								}
							}, 10*20);
							}
						}
					}
				}
			}
		}
	}
	public static ArrayList<Player> ddd = new ArrayList<>();
	public static ArrayList<Player> dec = new ArrayList<>();
	
	
	public static void onknock(Player p){
		Snowball snowb = (Snowball) Bukkit.getWorld(p.getWorld().getName()).spawnEntity(p.getLocation().add(0, 1, 1), EntityType.SNOWBALL);
		double x = p.getLocation().getX() - p.getLocation().add(0, 1, 1).getX();
		double y = p.getLocation().getY() - p.getLocation().add(0, 1, 1).getY();
		double z = p.getLocation().getZ() - p.getLocation().add(0, 1, 1).getZ();
		org.bukkit.util.Vector v = new org.bukkit.util.Vector(x, y, z).multiply(3);
		snowb.setVelocity(v);
	}
	
	
}
