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

import org.bukkit.configuration.file.FileConfiguration;

/**
 * Konfigurations Klasse.
 * 
 * @author Calenria
 * 
 */
public class ConfigData {

    private String server;
    private String hilfe;
    private String srvpm;
    private String global;
    private String team;
    private String lokal;
    private String spy;
    private String pmSpy;
    private String to;
    private Long radius;

    private String name;

    private String hostname;
    private int port;
    private String database;
    private String user;
    private String password;
    private String praefix;

    private Boolean debug = false;
    private Boolean lokalchat;
    private Boolean tablist;

    /**
     * @param plugin
     *            SimpleChat Plugin
     */
    public ConfigData(final SimpleChat plugin) {
        FileConfiguration config = plugin.getConfig();
        setServer(config.getString("server"));
        setHilfe(config.getString("hilfe"));
        setSrvpm(config.getString("srvpm"));

        setGlobal(config.getString("global"));
        setTeam(config.getString("team"));
        setLokal(config.getString("lokal"));
        setSpy(config.getString("spy"));
        setPmSpy(config.getString("pmspy"));
        setRadius(config.getLong("radius"));
        setTo(config.getString("to"));

        setName(config.getString("name"));
        setUser(config.getString("mysql.user"));
        setPassword(config.getString("mysql.password"));
        setHostname(config.getString("mysql.hostname"));
        setDatabase(config.getString("mysql.database"));
        setPraefix(config.getString("mysql.praefix"));
        setPort(config.getInt("mysql.port"));

        setDebug(config.getBoolean("debug"));
        setLokalchat(config.getBoolean("lokalchat", true));
        setTablist(config.getBoolean("tablist", true));
    }

    /**
     * @return the global
     */
    public String getGlobal() {
        return global;
    }

    /**
     * @return the hilfe
     */
    public String getHilfe() {
        return hilfe;
    }

    /**
     * @return the lokal
     */
    public String getLokal() {
        return lokal;
    }

    /**
     * @return the pmspy
     */
    public String getPmSpy() {
        return pmSpy;
    }

    /**
     * @return the radius
     */
    public Long getRadius() {
        return radius;
    }

    /**
     * @return the server
     */
    public String getServer() {
        return server;
    }

    /**
     * @return the spy
     */
    public String getSpy() {
        return spy;
    }

    /**
     * @return the srvpm
     */
    public String getSrvpm() {
        return srvpm;
    }

    /**
     * @return the team
     */
    public String getTeam() {
        return team;
    }

    /**
     * @param global
     *            the global to set
     */
    public void setGlobal(String global) {
        this.global = global;
    }

    /**
     * @param hilfe
     *            the hilfe to set
     */
    public void setHilfe(String hilfe) {
        this.hilfe = hilfe;
    }

    /**
     * @param lokal
     *            the lokal to set
     */
    public void setLokal(String lokal) {
        this.lokal = lokal;
    }

    /**
     * @param pmspy
     *            the pmspy to set
     */
    public void setPmSpy(String pmSpy) {
        this.pmSpy = pmSpy;
    }

    /**
     * @param radius
     *            the radius to set
     */
    public void setRadius(Long radius) {
        this.radius = radius;
    }

    /**
     * @param server
     *            the server to set
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * @param spy
     *            the spy to set
     */
    public void setSpy(String spy) {
        this.spy = spy;
    }

    /**
     * @param srvpm
     *            the srvpm to set
     */
    public void setSrvpm(String srvpm) {
        this.srvpm = srvpm;
    }

    /**
     * @param team
     *            the team to set
     */
    public void setTeam(String team) {
        this.team = team;
    }

    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to
     *            the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the hostname
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * @param hostname
     *            the hostname to set
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * @return the database
     */
    public String getDatabase() {
        return database;
    }

    /**
     * @param database
     *            the database to set
     */
    public void setDatabase(String database) {
        this.database = database;
    }

    /**
     * @return the praefix
     */
    public String getPraefix() {
        return praefix;
    }

    /**
     * @param praefix
     *            the praefix to set
     */
    public void setPraefix(String praefix) {
        this.praefix = praefix;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port
     *            the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return the debug
     */
    public Boolean getDebug() {
        return debug;
    }

    /**
     * @param debug
     *            the debug to set
     */
    public void setDebug(Boolean debug) {
        this.debug = debug;
    }

    /**
     * @return the lokalchat
     */
    public Boolean getLokalchat() {
        return lokalchat;
    }

    /**
     * @param lokalchat
     *            the lokalchat to set
     */
    public void setLokalchat(Boolean lokalchat) {
        this.lokalchat = lokalchat;
    }

    /**
     * @return the tablist
     */
    public synchronized Boolean getTablist() {
        return tablist;
    }

    /**
     * @param tablist
     *            the tablist to set
     */
    public synchronized void setTablist(Boolean tablist) {
        this.tablist = tablist;
    }

}
