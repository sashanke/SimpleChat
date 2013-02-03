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

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.github.calenria.simplechat.ChatMessage;
import com.github.calenria.simplechat.Chatter;
import com.github.calenria.simplechat.SimpleChat;

/**
 * Eventlistener Klasse.
 * 
 * @author Calenria
 * 
 */
public class SimpleChatListener implements Listener {
    /**
     * Bukkit Logger.
     */
    @SuppressWarnings("unused")
    private static Logger log    = Logger.getLogger("Minecraft");
    /**
     * SimpleChat Plugin.
     */
    private SimpleChat    plugin = null;

    /**
     * Registriert die Eventhandler
     * 
     * @param scPlugin
     *            SimpleChat Plugin
     */
    public SimpleChatListener(final SimpleChat scPlugin) {
        this.plugin = scPlugin;
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPlayerJoinEvent(final PlayerJoinEvent event) {
        final Player sPlayer = event.getPlayer();
        event.setJoinMessage(null);
        plugin.addPlayer(sPlayer);
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                plugin.setChatter(new Chatter(sPlayer.getName(), plugin.config.getServer()));
                String displayName = sPlayer.getName();
                if (plugin.config.getTablist()) {
                    displayName = plugin.chat.getPlayerPrefix(sPlayer) + sPlayer.getName();
                }
                String tabName = "@#@login@#@" + sPlayer.getName() + "@#@" + displayName;
                sPlayer.sendPluginMessage(plugin, "SimpleChat", tabName.getBytes());
            }
        });

    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerQuitEvent(final PlayerQuitEvent event) {
        event.setQuitMessage(null);
        plugin.removePlayer(event.getPlayer());
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerKickEvent(final PlayerKickEvent event) {
        event.setLeaveMessage(null);
        plugin.removePlayer(event.getPlayer());
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onChatEvent(final AsyncPlayerChatEvent event) {
        new ChatMessage(event, plugin);
    }

}
