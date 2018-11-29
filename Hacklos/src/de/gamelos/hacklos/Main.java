package de.gamelos.hacklos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener{

	public static File locations;
	public static FileConfiguration loc;
	public static HashMap<Player, Integer> clicks = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> invclicks = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> bowshots = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> anitscaffult = new HashMap<Player, Integer>();
	public static ArrayList<Player> hackingplayers = new ArrayList<Player>();
	public static ArrayList<Player> walkingplayers = new ArrayList<Player>();
	public static HashMap<Player,Double> hackerdetect = new HashMap<>();
	@Override
	public void onEnable() {

		Main.locations = new File("plugins/Hacklos", "settings.yml");
		getCommand("check").setExecutor(this);
		Main.loc = YamlConfiguration.loadConfiguration(Main.locations);
		Bukkit.getPluginManager().registerEvents(this, this);
		//config
		if(!locations.exists()){
			//..........................................................
			loc.set("Settings.AntiRange.enabling", true);
			loc.set("Settings.AntiRange.MaxRange", 4.5);
			loc.set("Settings.AntiClick.enabling", true);
			loc.set("Settings.AntiClick.MaxClicks", 2);
			loc.set("Settings.AntiAutoChestLoot.enabling", true);
			loc.set("Settings.AntiAutoChestLoot.MaxClicks", 9);
			loc.set("Settings.AntiAutoChestLoot.MaxcloseClicks", 10);
			loc.set("Settings.AntiInvWalk.enabling", true);
			loc.set("Settings.AntiGlide.enabling", true);
			loc.set("Settings.AntiNoFallDamage.enabling", true);
			loc.set("Settings.AntiFly.enabling", true);
			loc.set("Settings.AntiSpeed.enabling", true);
			loc.set("Settings.AntiGroundSpeed.enabling", true);
			loc.set("Settings.AntiKillAura.enabling", false);
			loc.set("Settings.AntiWaterwalk.enabling", true);
			loc.set("Settings.AntiNoSlowdown.enabling", false);
			loc.set("Settings.AntiFastBow.enabling", true);
			loc.set("Settings.AntiSceffaultWalk.enabling", true);
			loc.set("Settings.AntiFastPlace.enabling", true);
			//..........................................................
			//
			try {
				Main.loc.save(Main.locations);	
			} catch (IOException e) {
				e.printStackTrace();
			}
			//
		//config
		}
		
		Bukkit.getPluginManager().registerEvents(new HitListener(), this);
		Bukkit.getPluginManager().registerEvents(new Check(), this);
		
		//TODO load	
		if(loc.getBoolean("Settings.AntiRange.enabling")){
			System.out.println("[Hacklos] Enabling AntiRange");
			Bukkit.getPluginManager().registerEvents(new AntiRange(), this);
		}
		
		if(loc.getBoolean("Settings.AntiClick.enabling")){
			System.out.println("[Hacklos] Enabling AntiClick");
			startclickspeedreset();
			Bukkit.getPluginManager().registerEvents(new AntiClick(), this);
		}
		
		if(loc.getBoolean("Settings.AntiAutoChestLoot.enabling")){
//			System.out.println("[Hacklos] Enabling AntiAutoChestLoot");
//			Bukkit.getPluginManager().registerEvents(new AntiAutoChestLoot(), this);
		}
		
		if(loc.getBoolean("Settings.AntiInvWalk.enabling")){
			System.out.println("[Hacklos] Enabling AntiInvWalk");
			Bukkit.getPluginManager().registerEvents(new AntiInvWalk(), this);
		}
		
		if(loc.getBoolean("Settings.AntiGlide.enabling")){
			System.out.println("[Hacklos] Enabling AntiGlide");
			Bukkit.getPluginManager().registerEvents(new AntiGlide(), this);
		}
		
		if(loc.getBoolean("Settings.AntiNoFallDamage.enabling")){
			System.out.println("[Hacklos] Enabling AntiNoFallDamage");
			Bukkit.getPluginManager().registerEvents(new AntiNoFallDamage(), this);
		}
		
		
		if(loc.getBoolean("Settings.AntiFly.enabling")){
			System.out.println("[Hacklos] Enabling AntiFly");
			Bukkit.getPluginManager().registerEvents(new AntiFly(), this);
		}
		
		if(loc.getBoolean("Settings.AntiSpeed.enabling")){
			System.out.println("[Hacklos] Enabling AntiSpeed");
			Bukkit.getPluginManager().registerEvents(new AntiSpeed(), this);
		}
		
		if(loc.getBoolean("Settings.AntiGroundSpeed.enabling")){
			System.out.println("[Hacklos] Enabling AntiGroundSpeed");
			Bukkit.getPluginManager().registerEvents(new AntiGroundSpeed(), this);
		}
		
		
		if(loc.getBoolean("Settings.AntiWaterwalk.enabling")){
			System.out.println("[Hacklos] Enabling AntiWaterwalk");
			Bukkit.getPluginManager().registerEvents(new AntiWaterwalk(), this);
		}
		
		if(loc.getBoolean("Settings.AntiNoSlowdown.enabling")){
			System.out.println("[Hacklos] Enabling AntiNoSlowdown");
			Bukkit.getPluginManager().registerEvents(new AntiNoSlowdown(), this);
		}
		
		if(loc.getBoolean("Settings.AntiFastBow.enabling")){
			System.out.println("[Hacklos] Enabling AntiFastBow");
			Bukkit.getPluginManager().registerEvents(new AntiFastBow(), this);
		}
		
		if(loc.getBoolean("Settings.AntiSceffaultWalk.enabling")){
			System.out.println("[Hacklos] Enabling AntiSceffaultWalk");
			Bukkit.getPluginManager().registerEvents(new AntiSceffaultWalk(), this);
		}
		
		if(loc.getBoolean("Settings.AntiFastPlace.enabling")){
			System.out.println("[Hacklos] Enabling AntiFastPlace");
			Bukkit.getPluginManager().registerEvents(new AntiFastPlace(), this);
		}
		
		//TODO leadende
		super.onEnable();
		}

	public void loadConfig(){
		getConfig().options().copyDefaults(true);
		saveConfig();
		}
	
	@SuppressWarnings("deprecation")
	public static void startclickspeedreset(){
		Plugin pl = Bukkit.getPluginManager().getPlugin("Hacklos");
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(pl, new Runnable(){

			@Override
			public void run() {
				clicks.clear();
				AntiFastPlace.place.clear();
				AntiClick.cps.clear();
				AntiClick.info.clear();
				invclicks.clear();
				bowshots.clear();
				anitscaffult.clear();
			}
			
		}, 0, 20);
	}

	
//	@SuppressWarnings("deprecation")
//	public static void startfastclickspeedreset(){
//		Plugin pl = Bukkit.getPluginManager().getPlugin("Hacklos");
//		Bukkit.getScheduler().scheduleAsyncRepeatingTask(pl, new Runnable(){
//
//			@Override
//			public void run() {
//				walkingplayers.clear();
//			}
//			
//		}, 0, 5);
//	}
	
	public static void sendhackingmessageifhacking(Player p){
//		if(hackerdetect.containsKey(p)){
//			if(hackerdetect.get(p) > 5.0 && hackerdetect.get(p) <= 8.0){
//				for(Player pp : Bukkit.getOnlinePlayers()){
//					if(pp.isOp()){
//						pp.sendMessage(ChatColor.GOLD+"[Hacklos] "+ChatColor.RED+p.getName()+ChatColor.GRAY+" wurde als Hacker erkannt!");
//					}
//				}
//			}else if(hackerdetect.get(p) > 8.0){
////				p.kickPlayer(ChatColor.RED+"HACKING");
//			}
//		}
	}
	
	@SuppressWarnings("deprecation")
	public static void startreset(){
		Plugin pl = Bukkit.getPluginManager().getPlugin("Hacklos");
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(pl, new Runnable(){
			@Override
			public void run() {
				hackerdetect.clear();
			}
		}, 3600, 3600);
	}
	
//	===========================================================
	public static ArrayList<Player> Clickspeed = new ArrayList<>();
	public static ArrayList<Player> FastBow = new ArrayList<>();
	public static ArrayList<Player> FastPlace = new ArrayList<>();
	public static ArrayList<Player> Fly = new ArrayList<>();
	public static ArrayList<Player> Glide = new ArrayList<>();
	public static ArrayList<Player> GroundSpeed = new ArrayList<>();
	public static ArrayList<Player> NoFall = new ArrayList<>();
	public static ArrayList<Player> Range = new ArrayList<>();
	public static ArrayList<Player> Sceffault = new ArrayList<>();
	public static ArrayList<Player> Speed = new ArrayList<>();
	public static ArrayList<Player> Waterwalk = new ArrayList<>();
	public static ArrayList<Player> AutoArmor = new ArrayList<>();
//	===========================================================
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("check")){
			if(sender.hasPermission("hacklos.check")){
			if(args.length==1){
				if(Bukkit.getPlayer(args[0])!=null){
				Player pp = Bukkit.getPlayer(args[0]);
				Check.autoarmor(pp);
//				Check.onknock(pp);
//				============================================
				int points = 0;
				sender.sendMessage(ChatColor.GRAY+"====================================");
				if(Clickspeed.contains(pp)){
					int maxclickspeed = 0;
					if(AntiClick.maxclicks.containsKey(pp)){
						maxclickspeed = AntiClick.maxclicks.get(pp)*2;
					}
					sender.sendMessage(ChatColor.GRAY+"AutoClicker: "+ChatColor.GREEN+"YES "+ChatColor.GRAY+"("+maxclickspeed+")");
					points = points+maxclickspeed;
				}else{
				sender.sendMessage(ChatColor.GRAY+"AutoClicker: "+ChatColor.RED+"NO");
				}
				if(FastBow.contains(pp)){
					sender.sendMessage(ChatColor.GRAY+"FastBow: "+ChatColor.GREEN+"YES");
					points = points+100;
				}else{
				sender.sendMessage(ChatColor.GRAY+"FastBow: "+ChatColor.RED+"NO");
				}
				if(FastPlace.contains(pp)){
					points = points+25;
					sender.sendMessage(ChatColor.GRAY+"FastPlace: "+ChatColor.GREEN+"YES");
				}else{
				sender.sendMessage(ChatColor.GRAY+"FastPlace: "+ChatColor.RED+"NO");
				}
				if(Fly.contains(pp)){
					points = points+90;
					sender.sendMessage(ChatColor.GRAY+"Fly: "+ChatColor.GREEN+"YES");
				}else{
				sender.sendMessage(ChatColor.GRAY+"Fly: "+ChatColor.RED+"NO");
				}
				if(Glide.contains(pp)){
					points = points+100;
					sender.sendMessage(ChatColor.GRAY+"Glide: "+ChatColor.GREEN+"YES");
				}else{
				sender.sendMessage(ChatColor.GRAY+"Glide: "+ChatColor.RED+"NO");
				}
				if(GroundSpeed.contains(pp)){
					points = points+100;
					sender.sendMessage(ChatColor.GRAY+"GroundSpeed: "+ChatColor.GREEN+"YES");
				}else{
				sender.sendMessage(ChatColor.GRAY+"GroundSpeed: "+ChatColor.RED+"NO");
				}
				if(NoFall.contains(pp)){
					points = points+100;
					sender.sendMessage(ChatColor.GRAY+"NoFall: "+ChatColor.GREEN+"YES");
				}else{
				sender.sendMessage(ChatColor.GRAY+"NoFall: "+ChatColor.RED+"NO");
				}
				if(Range.contains(pp)){
					points = points+100;
					sender.sendMessage(ChatColor.GRAY+"Range: "+ChatColor.GREEN+"YES");
				}else{
				sender.sendMessage(ChatColor.GRAY+"Range: "+ChatColor.RED+"NO");
				}
				if(Sceffault.contains(pp)){
					points = points+50;
					sender.sendMessage(ChatColor.GRAY+"Scaffault: "+ChatColor.GREEN+"YES");
				}else{
				sender.sendMessage(ChatColor.GRAY+"Scaffault: "+ChatColor.RED+"NO");
				}
				if(Speed.contains(pp)){
					points = points+100;
					sender.sendMessage(ChatColor.GRAY+"Speed: "+ChatColor.GREEN+"YES");
				}else{
				sender.sendMessage(ChatColor.GRAY+"Speed: "+ChatColor.RED+"NO");
				}
				if(Waterwalk.contains(pp)){
					points = points+80;
					sender.sendMessage(ChatColor.GRAY+"Waterwalk: "+ChatColor.GREEN+"YES");
				}else{
				sender.sendMessage(ChatColor.GRAY+"Waterwalk: "+ChatColor.RED+"NO");
				}
				if(AutoArmor.contains(pp)){
					sender.sendMessage(ChatColor.GRAY+"AutoArmor: "+ChatColor.GREEN+"YES");
				}else{
				sender.sendMessage(ChatColor.GRAY+"AutoArmor: "+ChatColor.RED+"NO");
				}
				sender.sendMessage(" ");
				int prozent = 0;
				
				if(points>=100){
					prozent = 100;
				}else{
					prozent = points;
				}
				
				sender.sendMessage(ChatColor.GRAY+"Hackwarscheinlichkeit: "+ChatColor.YELLOW+points+ChatColor.GRAY+"/100 ("+ChatColor.YELLOW+prozent+"%"+ChatColor.GRAY+")");
				sender.sendMessage(ChatColor.GRAY+"====================================");
//				============================================
				}else{
					sender.sendMessage(ChatColor.RED+"Dieser Spieler ist nicht Online");
				}
			}else{
				sender.sendMessage(ChatColor.RED+"Nutze /check <Spielername>");
			}
			}else{
				sender.sendMessage(ChatColor.RED+"Du hast keine Rechte dazu");
			}
		}
		return super.onCommand(sender, cmd, label, args);
	}
	
	
	
}
