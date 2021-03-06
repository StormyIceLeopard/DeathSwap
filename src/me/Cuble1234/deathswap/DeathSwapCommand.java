package me.Cuble1234.deathswap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeathSwapCommand implements CommandExecutor {
	
	SettingsManager sm = SettingsManager.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "");
			return true;
		}
		
		Player p = (Player) sender;
		
		if(!p.hasPermission("deathswap.command.start")) {
			p.sendMessage(ChatColor.RED + "You do not have permission to do this command!");
		}
		
		if(cmd.getName().equalsIgnoreCase("ds")) {
			if (!(args.length == 1)) {
				p.sendMessage(ChatColor.RED + "/ds start");
				return true;
			}
			
			if (!args[0].equalsIgnoreCase("start")) {
				p.sendMessage(ChatColor.RED + "/ds start");
				return true;
			}
			
			if (Bukkit.getServer().getOnlinePlayers().size() < 2) {
				p.sendMessage(ChatColor.RED + "You need more than 2 players");
			}
			
			if (Bukkit.getServer().getOnlinePlayers().size() > 2 && args.length != 3) {
				p.sendMessage(ChatColor.RED + "You are more than 2 pepole in the server, please specify 2 players!");
				return true;
			}
			if(args.length == 3) {
				sm.setPlayers(Bukkit.getServer().getPlayer(args[1]),0);
				if (sm.getPlayers()[0] == null) {
					p.sendMessage(ChatColor.RED + "Could not find the player " + args[1] + "!");
					return true;
				}
				sm.setPlayers(Bukkit.getServer().getPlayer(args[2]),1);
				if (sm.getPlayers()[1] == null) {
					p.sendMessage(ChatColor.RED + "Could not find the player " + args[2] + "!");
					return true;
				}
			}
			if(Bukkit.getServer().getOnlinePlayers().size() == 2 && args.length == 1) {
				int i = 0;
				for (Player player : Bukkit.getServer().getOnlinePlayers()) {
					sm.setPlayers(player,i);
					i++;
				}
			}
			DeathSwapHandler d = new DeathSwapHandler();
			d.deathSwap();
			
		}
		return true;
	}

}
