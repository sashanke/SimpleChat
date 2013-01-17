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

import com.dthielke.herochat.ChannelChatEvent;
import com.dthielke.herochat.Chatter.Result;
import com.github.calenria.simplechat.SimpleChat;
import com.github.calenria.simplechat.Utils;

/**
 * Eventlistener Klasse.
 * 
 * @author Calenria
 * 
 */
public class SimpleHeroChatListener implements Listener {
    // /**
    // * Bukkit Logger.
    // */
    // private static Logger log = Logger.getLogger("Minecraft");
    /**
     * NextVote Plugin.
     */
    private SimpleChat    plugin = null;

    /**
     * Registriert die Eventhandler und erstellt die Datenbank falls nicht vorhanden.
     * 
     * @param nvPlugin
     *            NextVote Plugin
     */
    public SimpleHeroChatListener(final SimpleChat nvPlugin) {
        this.plugin = nvPlugin;
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onChannelChatEvent(final ChannelChatEvent event) {
        String message = event.getMessage().trim();
        if (event.getResult() == Result.ALLOWED) {
            if (event.getChannel().getName().equals("Server")) {
                String pMsg = Utils.replacePlayerName(plugin.config.getServer(), Bukkit.getPlayer(event.getSender().getPlayer().getName()), plugin.chat.getPlayerPrefix(Bukkit.getPlayer(event.getSender().getPlayer().getName())), message);
                event.getSender().getPlayer().sendPluginMessage(plugin, "SimpleChat", pMsg.getBytes());
            }
            if (event.getChannel().getName().equals("Hilfe")) {
                String pMsg = Utils.replacePlayerName(plugin.config.getHilfe(), Bukkit.getPlayer(event.getSender().getPlayer().getName()), plugin.chat.getPlayerPrefix(Bukkit.getPlayer(event.getSender().getPlayer().getName())), message);
                event.getSender().getPlayer().sendPluginMessage(plugin, "SimpleChat", pMsg.getBytes());
            }
        }
    }
}
