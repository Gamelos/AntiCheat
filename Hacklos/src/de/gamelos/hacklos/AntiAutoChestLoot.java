package de.gamelos.hacklos;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;



public class AntiAutoChestLoot implements Listener {

	
	@EventHandler
	public static void onclick(InventoryClickEvent e){
		Entity hitter1 = e.getWhoClicked();
		
		if(hitter1 instanceof Player){
			Player hitter = (Player) hitter1;
			
			if(hitter.getGameMode() == GameMode.CREATIVE){
				return;
			}
			
			int inv = 0;
			for(ItemStack s : e.getInventory().getContents()){
				if(s!=null){
				inv++;
				}
			}
		
			if(inv > 12){
				return;
			}
			
		if(e.getCurrentItem().getType() != Material.AIR){	
			int clicksnow;
			if(Main.invclicks.containsKey(hitter)){
				int click = Main.invclicks.get(hitter);
				clicksnow = click+1;
			}else{
				clicksnow = 1;
			}
			Main.invclicks.put(hitter, clicksnow);
		}else{
			Main.invclicks.remove(hitter);
		}
		
			int clicksnow1;
			if(Main.invclicks.containsKey(hitter)){
				clicksnow1 = Main.invclicks.get(hitter);
			}else{
				clicksnow1 = 0;
			}
			
			
			if(clicksnow1 >= Main.loc.getInt("Settings.AntiAutoChestLoot.MaxClicks")){
//				e.setCancelled(true);
				e.getWhoClicked().sendMessage("AutoChestLoot");
			}
			
			if(clicksnow1 >= Main.loc.getInt("Settings.AntiAutoChestLoot.MaxcloseClicks")){
				Player p = (Player) e.getWhoClicked();
//				====================================
				if(Main.hackerdetect.containsKey(p)){
					Main.hackerdetect.put(p, Main.hackerdetect.get(p)+1.0);
				}else{
					Main.hackerdetect.put(p, 1.0);
				}
				Main.sendhackingmessageifhacking(p);
//				===================================
			}
		}
	}
	
	
	
}
