package me.imzomi.uhcscenarios.scenarios;

import me.imzomi.uhcscenarios.Main;
import me.imzomi.uhcscenarios.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class EnchantedDeath
        implements Listener, CommandExecutor {

    private Main plugin;
    public EnchantedDeath
            (Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void EnchantedDeath(EntityDeathEvent e){
        if (plugin.EnchantedDeath){
            if (e.getEntity().getType() == EntityType.PLAYER){
                if (Main.TimeBomb){
                    return;
                }
                ItemStack Enchants = new ItemStack(Material.ENCHANTING_TABLE, 1);
                e.getDrops().add(Enchants);
            }
        }
    }
    @EventHandler
    public void enchantedDeath(CraftItemEvent e){
        if (plugin.EnchantedDeath){
            if (e.getRecipe().getResult().getType() == Material.ENCHANTING_TABLE){
                e.setCancelled(true);
            }
        }
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (sender.hasPermission("uhc.admin") && cmd.getName().equalsIgnoreCase("EnchantedDeath")) {
            if (!plugin.EnchantedDeath) {
                Bukkit.broadcastMessage(Utils.chat(Main.prefix + "&fEnchantedDeath has been " + Main.enabled));
                plugin.EnchantedDeath = Boolean.valueOf(true);
            } else {
                Bukkit.broadcastMessage(Utils.chat(Main.prefix + "&fEnchantedDeath has been " + Main.disabled));
                plugin.EnchantedDeath = Boolean.valueOf(false);
            }
        } else {
            p.sendMessage(ChatColor.RED + "No tienes permisos para utilizar este comando");
        }
        return false;
    }
}
