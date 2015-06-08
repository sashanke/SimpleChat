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

import com.github.calenria.simplechat.Chatter;
import com.github.calenria.simplechat.SimpleChat;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.StringTokenizer;
import java.util.logging.Logger;

/**
 * Eventlistener Klasse.
 * 
 * @author Calenria
 * 
 */
public class SimpleChatPluginListener implements PluginMessageListener {
    /**
     * Bukkit Logger.
     */
    private static Logger log = Logger.getLogger("Minecraft");
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
    public SimpleChatPluginListener(final SimpleChat nvPlugin) {
        this.plugin = nvPlugin;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {

        String rawMessage = new String(message);
        if (plugin.config.getDebug()) {
            log.info("Recived plugin message: " + rawMessage);
        }
        
        log.info("Recived plugin message: " + rawMessage);
        

        if (!channel.equals("BungeeCord")) {
            return;
        }

        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("SimpleChat")) {

            String pluginMessage = in.readUTF();

            StringTokenizer st = new StringTokenizer(pluginMessage, "@#@");
            String type = st.nextToken();

            if (type.equals("ping")) {
                plugin.updateCurrOnline();
                return;
            }

            if (type.equals("wp")) {
                String whisperPartner = st.nextToken();
                String chatter = st.nextToken();
                Chatter c = plugin.getChatter(chatter);
                plugin.removeChatter(chatter);
                c.setLastWhisperFrom(whisperPartner);
                plugin.setChatter(c);
                return;
            }

            String serverName = st.nextToken();
            if (plugin.config.getDebug()) {
                log.info("Message From Servername: " + serverName);
            }
            if (st.hasMoreTokens()) {
                String pluginChannel = st.nextToken();
                @SuppressWarnings("unused")
                String sender = st.nextToken();
                String chatMessage = st.nextToken();
                sendMessage(pluginChannel, chatMessage);
            }
        }

    }

    private void sendMessage(String pluginChannel, String message) {
        // Check vor players that toggled the global chat
        if (!pluginChannel.equals("Global")) {
            Bukkit.broadcast(message, "simplechat." + pluginChannel.toLowerCase());
        } else {
            Player[] players = Bukkit.getOnlinePlayers();
            for (Player player : players) {
                if (player.hasPermission("simplechat." + pluginChannel.toLowerCase()) && !player.hasPermission("simplechat.gobal.off")) {
                    player.sendMessage(message);
                }
            }
        }

    }

}
