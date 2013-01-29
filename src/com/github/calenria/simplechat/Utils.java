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
package com.github.calenria.simplechat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

/**
 * Utility Klasse für alles mögliche.
 * 
 * @author Calenria
 */
public final class Utils {
    /**
     * 1 Sekunden.
     */
    public static final long                       TASK_ONE_SECOND    = 20L;

    /**
     * 3 Sekunden.
     */
    public static final long                       TASK_THREE_SECONDS = 60L;

    /**
     * 1 Minute.
     */
    public static final long                       TASK_ONE_MINUTE    = 1200L;

    /**
     * FileBuffer.
     */
    private static final int                       BUFFER             = 1024;

    /**
     * HashMap mit allen Minecraft Farben.
     */
    public static final HashMap<String, ChatColor> COLORMAP           = new HashMap<String, ChatColor>() {
                                                                          private static final long serialVersionUID = 1L;
                                                                          {
                                                                              put("<AQUA>", ChatColor.AQUA);
                                                                              put("<BLACK>", ChatColor.BLACK);
                                                                              put("<BLUE>", ChatColor.BLUE);
                                                                              put("<BOLD>", ChatColor.BOLD);
                                                                              put("<DARK_AQUA>", ChatColor.DARK_AQUA);
                                                                              put("<DARK_BLUE>", ChatColor.DARK_BLUE);
                                                                              put("<DARK_GRAY>", ChatColor.DARK_GRAY);
                                                                              put("<DARK_GREEN>", ChatColor.DARK_GREEN);
                                                                              put("<DARK_PURPLE>", ChatColor.DARK_PURPLE);
                                                                              put("<DARK_RED>", ChatColor.DARK_RED);
                                                                              put("<GOLD>", ChatColor.GOLD);
                                                                              put("<GRAY>", ChatColor.GRAY);
                                                                              put("<GREEN>", ChatColor.GREEN);
                                                                              put("<ITALIC>", ChatColor.ITALIC);
                                                                              put("<LIGHT_PURPLE>", ChatColor.LIGHT_PURPLE);
                                                                              put("<MAGIC>", ChatColor.MAGIC);
                                                                              put("<RED>", ChatColor.RED);
                                                                              put("<RESET>", ChatColor.RESET);
                                                                              put("<STRIKETHROUGH>", ChatColor.STRIKETHROUGH);
                                                                              put("<UNDERLINE>", ChatColor.UNDERLINE);
                                                                              put("<WHITE>", ChatColor.WHITE);
                                                                              put("<YELLOW>", ChatColor.YELLOW);
                                                                          }
                                                                      };

    /**
     * HashMap mit allen Minecraft Feuerwerks Farben.
     */
    public static final HashMap<String, Color>     COLORFIREWORKMAP   = new HashMap<String, Color>() {
                                                                          private static final long serialVersionUID = 1L;
                                                                          {
                                                                              put("AQUA", Color.AQUA);
                                                                              put("BLACK", Color.BLACK);
                                                                              put("BLUE", Color.BLUE);
                                                                              put("BLUE", Color.FUCHSIA);
                                                                              put("GRAY", Color.GRAY);
                                                                              put("GREEN", Color.GREEN);
                                                                              put("LIME", Color.LIME);
                                                                              put("MAROON", Color.MAROON);
                                                                              put("NAVY", Color.NAVY);
                                                                              put("OLIVE", Color.OLIVE);
                                                                              put("ORANGE", Color.ORANGE);
                                                                              put("PURPLE", Color.PURPLE);
                                                                              put("RED", Color.RED);
                                                                              put("SILVER", Color.SILVER);
                                                                              put("TEAL", Color.TEAL);
                                                                              put("WHITE", Color.WHITE);
                                                                              put("YELLOW", Color.YELLOW);
                                                                          }
                                                                      };

    /**
     * Ersetzt Farben und Platzhalter.
     * 
     * @param message
     *            Nachricht in der Farben und Platzhalter ersetzt werden
     * @param args
     *            Argumente für die Platzhalter
     * @return Ersetzter String
     */
    public static String colorFormat(final String message, final Object... args) {
        return ChatColor.translateAlternateColorCodes('&', String.format(message, args));
    }

    /**
     * Einfache Methode um Dateien zu Kopieren.
     * 
     * @param input
     *            Quellstream
     * @param target
     *            Zieldatei
     * @throws IOException
     *             Falls Zieldatei Existiert oder Fehler beim erstellen der Zieldatei
     */
    public static void copy(final InputStream input, final File target) throws IOException {
        if (target.exists()) {
            throw new IOException("File already exists!");
        }

        if (!target.createNewFile()) {
            throw new IOException("Failed at creating new empty file!");
        }
        byte[] buffer = new byte[BUFFER];

        OutputStream output = new FileOutputStream(target);

        int realLength;

        while ((realLength = input.read(buffer)) > 0) {
            output.write(buffer, 0, realLength);
        }

        output.flush();
        output.close();
    }

    /**
     * Berechnet die Tage zwischen zwei Daten.
     * 
     * @param startDate
     *            Start Datum
     * @param endDate
     *            End Datum
     * @return long Anzahl der Tage
     */
    public static long daysBetween(final Calendar startDate, final Calendar endDate) {
        Calendar date = (Calendar) startDate.clone();
        long daysBetween = 0;
        while (date.before(endDate)) {
            date.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return daysBetween;
    }

    /**
     * Berechnet die Tage zwischen zwei Tagen jeweils ab Mitternacht.
     * 
     * @param startDate
     *            Start Datum
     * @param endDate
     *            End Datum
     * @return long Anzahl der Tage
     */
    public static long daysBetweenMidnight(final Date startDate, final Date endDate) {
        Calendar startCal = new GregorianCalendar();
        startCal.setTime(startDate);
        startCal.set(Calendar.HOUR_OF_DAY, 0);
        startCal.set(Calendar.MINUTE, 0);
        startCal.set(Calendar.SECOND, 0);
        startCal.set(Calendar.MILLISECOND, 0);

        Calendar endCal = new GregorianCalendar();
        endCal.setTime(endDate);
        endCal.set(Calendar.HOUR_OF_DAY, 0);
        endCal.set(Calendar.MINUTE, 0);
        endCal.set(Calendar.SECOND, 0);
        endCal.set(Calendar.MILLISECOND, 0);

        Calendar date = (Calendar) startCal.clone();

        long daysBetween = 0;
        while (date.before(endCal)) {
            date.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return daysBetween;
    }

    /**
     * Fast ein Array zu einem String zusammen.
     * 
     * @param inputArray
     *            Das eigentliche Array
     * @param glueString
     *            Das/Die Zeichen die benutz werden um die Arrayeinträge zu verbinden
     * @return String
     */
    public static String implodeArray(final String[] inputArray, final String glueString) {
        String output = "";
        if (inputArray.length > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(inputArray[0]);

            for (int i = 1; i < inputArray.length; i++) {
                sb.append(glueString);
                sb.append(inputArray[i]);
            }
            output = sb.toString();
        }
        return output;
    }

    /**
     * Überprüft ob der Spieler jemals auf dem Server gespielt hat und sendet eine Nachricht wenn nicht.
     * 
     * @param playerName
     *            Spieler der geprüft wird
     * @param sender
     *            Spieler an den die Nachricht ausgegeben wird
     * @return OfflinePlayer
     */
    public static OfflinePlayer offlinePlayerWithMessage(final String playerName, final String sender) {
        OfflinePlayer oPlayer = Bukkit.getOfflinePlayer(playerName);
        if (!oPlayer.hasPlayedBefore()) {
            Bukkit.getPlayer(sender).sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("&6%s&f nicht gefunden", playerName)));
            return null;
        } else {
            return oPlayer;

        }
    }

    /**
     * Ersetzt Platzhalter für Farben mit den entsprechenden Minecraft Farben.
     * 
     * @param text
     *            Text der durchsucht werden soll
     * @return String mit ersetzten Farben
     */
    public static String replaceColors(final String text) {
        String search = text.trim();
        for (String replKey : COLORMAP.keySet()) {
            search = search.replaceAll(replKey.toLowerCase(), String.valueOf(COLORMAP.get(replKey).toString()));
        }
        return ChatColor.translateAlternateColorCodes('&', search);
    }

    public static String replaceFrom(final String text, final String from, SimpleChat plugin) {
        String search = text.trim();
        String prefix = "";
        String fromPlayer = from;
        try {
            prefix = plugin.chat.getPlayerPrefix(Bukkit.getPlayer(from));
        } catch (Exception e) {
            // TODO: handle exception
        }
        try {
            fromPlayer = Bukkit.getPlayer(from).getName();
        } catch (Exception e) {
            // TODO: handle exception
        }
        search = search.replaceAll("<from>", prefix + fromPlayer);
        search = replaceColors(search);
        return search;
    }

    /**
     * Ersetzt Platzhalter für Farben und Spielernamen.
     * 
     * @param text
     *            Text der durchsucht werden soll
     * @param player
     *            Spielername
     * @return String mit ersetzten Farben und Spielernamen
     */
    public static String replacePlayerName(final String text, final Player player) {
        String search = text.trim();
        search = search.replaceAll("<player>", player.getName());
        search = search.replaceAll("<playerdn>", player.getDisplayName());
        search = replaceColors(search);
        return search;
    }

    /**
     * Ersetzt Platzhalter für Farben, Tage und Spielernamen.
     * 
     * @param text
     *            Text der durchsucht werden soll
     * @param player
     *            Spielername
     * @param days
     *            Spielername
     * @return String mit ersetzten Farben, Tagen und Spielernamen
     */
    public static String replacePlayerName(final String text, final Player player, final String prefix) {
        String search = text.trim();
        search = search.replaceAll("<prefix>", prefix);
        search = replacePlayerName(search, player);
        search = replaceColors(search);
        return search;
    }

    public static String replacePlayerName(final String text, final Player player, final String prefix, final String msg) {
        String search = text.trim();
        search = search.replaceAll("<prefix>", prefix);
        search = search.replaceAll("<msg>", msg);
        search = replacePlayerName(search, player);
        search = replaceColors(search);
        return search;
    }

    public static String replaceTo(final String text, final String to, final String msg, SimpleChat plugin) {
        String search = text.trim();
        String prefix = "";
        String toPlayer = to;
        try {
            prefix = plugin.chat.getPlayerPrefix(Bukkit.getPlayer(to));
        } catch (Exception e) {
            // TODO: handle exception
        }

        try {
            toPlayer = Bukkit.getPlayer(to).getName();
        } catch (Exception e) {
            // TODO: handle exception
        }

        search = search.replaceAll("<to>", prefix + toPlayer);
        search = search.replaceAll("<msg>", msg);
        search = replaceColors(search);
        return search;
    }

    /**
     * Keine Initalisierung!
     */
    private Utils() {
    }

}
