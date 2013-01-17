/*
 * Copyright (C) 2012 Calenria <https://github.com/Calenria/> and contributors
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3.0 of the License, or (at your option)
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 */
package com.github.calenria.simplechat.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.github.calenria.simplechat.SimpleChat;
import com.github.calenria.simplechat.Utils;

/**
 * Eventlistener Klasse.
 * 
 * @author Calenria
 * 
 */
public class SimpleChatListener implements Listener {
    // /**
    // * Bukkit Logger.
    // */
    // private static Logger log = Logger.getLogger("Minecraft");
    /**
     * NextVote Plugin.
     */
    private SimpleChat plugin = null;

    /**
     * Registriert die Eventhandler und erstellt die Datenbank falls nicht vorhanden.
     * 
     * @param nvPlugin
     *            NextVote Plugin
     */
    public SimpleChatListener(final SimpleChat nvPlugin) {
        this.plugin = nvPlugin;
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onChatEvent(final AsyncPlayerChatEvent event) {
        String message = event.getMessage().trim();
        int length = message.length();
        boolean server = message.startsWith("#");
        boolean help = message.startsWith("?");
        boolean pm = message.startsWith("@");

        if (pm && length > 2) {
            message = message.substring(1).trim();
            String[] parts = message.split(" ");
            message = message.substring(parts[0].length()).trim();
            String msg = Utils.replaceFrom(plugin.config.getSrvpm(), event.getPlayer().getName(), plugin);
            String pMsg = "@#@" + event.getPlayer().getName() + "@#@" + parts[0] + "@#@" + msg + "@#@" + message;
            event.getPlayer().sendPluginMessage(plugin, "SimpleChat", pMsg.getBytes());
            event.setCancelled(true);
        }

        if (!plugin.herochat) {
            if (server && length > 2) {
                String pMsg = Utils.replacePlayerName(plugin.config.getServer(), Bukkit.getPlayer(event.getPlayer().getName()), plugin.chat.getPlayerPrefix(Bukkit.getPlayer(event.getPlayer().getName())), message.substring(1).trim());
                // String pMsg = event.getPlayer().getName() + "@#@" + event.getPlayer().getDisplayName() + "@#@" + event.getFormat() + "@#@" + event.getMessage();
                event.getPlayer().sendPluginMessage(plugin, "SimpleChat", pMsg.getBytes());
                Bukkit.broadcastMessage(pMsg);
                event.setCancelled(true);
            }
            if (help && length > 2) {
                String pMsg = Utils.replacePlayerName(plugin.config.getHilfe(), Bukkit.getPlayer(event.getPlayer().getName()), plugin.chat.getPlayerPrefix(Bukkit.getPlayer(event.getPlayer().getName())), message.substring(1).trim());
                // String pMsg = event.getPlayer().getName() + "@#@" + event.getPlayer().getDisplayName() + "@#@" + event.getFormat() + "@#@" + event.getMessage();
                event.getPlayer().sendPluginMessage(plugin, "SimpleChat", pMsg.getBytes());
                Bukkit.broadcastMessage(pMsg);
                event.setCancelled(true);
            }

        }

    }

}
