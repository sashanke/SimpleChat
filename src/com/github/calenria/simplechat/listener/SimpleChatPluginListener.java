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
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.github.calenria.simplechat.SimpleChat;
import com.github.calenria.simplechat.Utils;

/**
 * Eventlistener Klasse.
 * 
 * @author Calenria
 * 
 */
public class SimpleChatPluginListener implements PluginMessageListener {
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
    public SimpleChatPluginListener(final SimpleChat nvPlugin) {
        this.plugin = nvPlugin;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player sPlayer, byte[] byteMessage) {
        if (!channel.equals("SimpleChat"))
            return;
        String stringMessage = new String(byteMessage);

        if (stringMessage.startsWith("@#@")) {
            String[] data = stringMessage.substring(3).split("@#@");
            String to = "";

            if (data[1] != null && data[1].length() >= 0) {
                to = data[1];
            }
            String msg = Utils.replaceTo(data[2], to,data[3],plugin);
            sPlayer.sendMessage(msg);
        } else {
            Bukkit.broadcastMessage(stringMessage);
        }

    }

}
