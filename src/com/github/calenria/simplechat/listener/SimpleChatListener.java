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

import com.github.calenria.simplechat.ChatMessage;
import com.github.calenria.simplechat.Chatter;
import com.github.calenria.simplechat.SimpleChat;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import me.zford.jobs.container.JobsPlayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.logging.Logger;

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
    private static Logger log = Logger.getLogger("Minecraft");
    /**
     * SimpleChat Plugin.
     */
    private SimpleChat plugin = null;

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
                    displayName = parsePlayerName(sPlayer, plugin.config.getName());
                }
                String tabName = "@#@login@#@" + sPlayer.getName() + "@#@" + displayName;
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("SimpleChat");
                out.writeUTF(tabName);
                sPlayer.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
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

    // TODO: util
    public String parsePlayerName(Player player, String format) {
        String parsedFormat = format;
        if (plugin.chat != null) {
            parsedFormat = parsedFormat.replace("<prefix>", plugin.chat.getPlayerPrefix(player));
            parsedFormat = parsedFormat.replace("<suffix>", plugin.chat.getPlayerSuffix(player));
            parsedFormat = parsedFormat.replace("<group>", plugin.chat.getPrimaryGroup(player));
        } else {
            parsedFormat = parsedFormat.replace("<prefix>", "");
            parsedFormat = parsedFormat.replace("<suffix>", "");
            parsedFormat = parsedFormat.replace("<group>", "");
        }

        parsedFormat = ChatColor.translateAlternateColorCodes('&', parsedFormat);
        parsedFormat = parsedFormat.replace("<server>", plugin.config.getServer());

        if (SimpleChat.jobs) {
            JobsPlayer jPlayer = SimpleChat.jobsPlugin.getPlayerManager().getJobsPlayer(player.getName());
            String honorific = "";
            if (jPlayer != null)
                honorific = jPlayer.getDisplayHonorific();
            parsedFormat = parsedFormat.replace("{jobs}", honorific);
        }

        return parsedFormat = parsedFormat.replace("<player>", player.getName());
    }

}
