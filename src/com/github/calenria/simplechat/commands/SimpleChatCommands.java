package com.github.calenria.simplechat.commands;

import java.util.Arrays;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.github.calenria.bungeetools.zBungeeTools;
import com.github.calenria.simplechat.SimpleChat;
import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandPermissions;

public class SimpleChatCommands {
    /**
    *
    */
    final private SimpleChat plugin;

    /**
     * @param nvPlugin
     *            Plugin
     * @return
     */
    public SimpleChatCommands(final SimpleChat nvPlugin) {
        this.plugin = nvPlugin;
    }

    @Command(aliases = { "w", "tell", "msg" }, desc = "Flüstert mit einem Spieler. Ohne Nachricht benutzen um eine Privaten Kanal zu erstellen", usage = "spieler [nachricht]")
    @CommandPermissions("simplechat.privat")
    public final void w(final CommandContext args, final CommandSender sender) throws CommandException {
        if (args.argsLength() == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Chatpartner (" + plugin.getChatter(sender.getName()).getConversionPartner() + ") gelöscht"));
            plugin.removeConversionPartner(sender.getName());
            return;
        }

        if (args.argsLength() == 1 && !plugin.getChatter(sender.getName()).isConversion()) {
            String cPlayer = zBungeeTools.getOnlinePlayer(args.getString(0));
            if (cPlayer == null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Fehler beim Ermitteln des Chat Partners. Offline oder Verschrieben? (" + args.getString(0) + ")"));
                return;
            } else {
                plugin.setConversionPartner(sender.getName(), cPlayer);
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Alle weiteren Nachrichten werden an (" + cPlayer + ") gesendet. Zum Beenden &4@@&6 oder &4/w&6 eingeben"));
                return;
            }
        }

        if (plugin.getChatter(sender.getName()).isConversion()) {
            AsyncPlayerChatEvent chatEvent = new AsyncPlayerChatEvent(false, Bukkit.getPlayer(sender.getName()), args.getJoinedStrings(0), new HashSet<Player>(Arrays.asList(Bukkit.getOnlinePlayers())));
            chatEvent.setFormat("%1$s -> %2$s");
            Bukkit.getServer().getPluginManager().callEvent(chatEvent);
        } else {
            AsyncPlayerChatEvent chatEvent = new AsyncPlayerChatEvent(false, Bukkit.getPlayer(sender.getName()), "@" + args.getString(0) + " " + args.getJoinedStrings(1), new HashSet<Player>(Arrays.asList(Bukkit.getOnlinePlayers())));
            chatEvent.setFormat("%1$s -> %2$s");
            Bukkit.getServer().getPluginManager().callEvent(chatEvent);
        }
    }

    @Command(aliases = { "rep", "r", "reply" }, desc = "Antwortet einem Spieler der dir zuvor geschrieben hat", usage = "nachricht")
    @CommandPermissions("simplechat.privat")
    public final void rep(final CommandContext args, final CommandSender sender) throws CommandException {
        if (plugin.getChatter(sender.getName()).isLastWhisper()) {
            AsyncPlayerChatEvent chatEvent = new AsyncPlayerChatEvent(false, Bukkit.getPlayer(sender.getName()), "@" + plugin.getChatter(sender.getName()).getLastWhisperFrom() + " " + args.getJoinedStrings(0), new HashSet<Player>(Arrays.asList(Bukkit.getOnlinePlayers())));
            chatEvent.setFormat("%1$s -> %2$s");
            Bukkit.getServer().getPluginManager().callEvent(chatEvent);
        }
    }

    @Command(aliases = { "globalchat" }, desc = "Blendet den Globalen Chat aus", usage = "", min = 0, max = 0)
    public final void globalchat(final CommandContext args, final CommandSender sender) throws CommandException {
        Player player = Bukkit.getPlayer(sender.getName());
        if (player.hasPermission("simplechat.gobal.off")) {
            SimpleChat.getPermission().playerRemove(player, "simplechat.gobal.off");
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Globalen Chat betreten, tippe &4/globalchat&6 erneut um den Globalen Chat zu verlassen!"));
        } else {
            SimpleChat.getPermission().playerAdd(player, "simplechat.gobal.off");
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Globalen Chat verlassen, tippe &4/globalchat&6 erneut um wieder im Globalen Chat lesen und schreiben zu dürfen!"));
        }
    }
}
