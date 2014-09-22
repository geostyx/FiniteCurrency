/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geostyx.finitecurrency;

/**
 *
 * @author Jacob
 */
// import net.canarymod.hook.Hook;
import java.util.Date;
import java.util.HashMap;
import net.canarymod.chat.Colors;
import net.canarymod.database.Database;
import net.canarymod.database.exceptions.DatabaseReadException;
import net.canarymod.database.exceptions.DatabaseWriteException;
import net.canarymod.hook.HookHandler;
import net.canarymod.hook.player.ConnectionHook;
import net.canarymod.hook.player.DisconnectionHook;
import net.canarymod.plugin.PluginListener;

public class FiniteListener implements PluginListener {

    @HookHandler
    public void onLogin(ConnectionHook hook) {
        FiniteCurrency.logger.info("Player Joined: " + hook.getPlayer().getName());
        hook.getPlayer().message(Colors.YELLOW + "Hello World, " + hook.getPlayer().getName());

        LogoutDataAccess da = new LogoutDataAccess();
        try {
            //Read data and put it into our (currently empty) dataaccess instance
            // Database.get().load(da, new String[] {{"player_name"}, hook.getPlayer().getName()});
            HashMap<String, Object> filter = new HashMap<String, Object>();
            filter.put("player_name", hook.getPlayer().getName());
            Database.get().load(da, filter);
        } catch (DatabaseReadException e) {
            //Whoops! Derp.
        }
        if (da.hasData()) {
            hook.getPlayer().message(String.valueOf(System.currentTimeMillis()) + " | " + String.valueOf(da.timestamp));
            hook.getPlayer().message("Your last login was " + ((System.currentTimeMillis()  - da.timestamp) / 1000L / 60 / 60) + " hours ago.");
        } else {
            hook.getPlayer().message("Welcome to our Server!");
        }

    }

    @HookHandler
    public void logout(DisconnectionHook hook) {
        LogoutDataAccess da = new LogoutDataAccess();
        da.player = hook.getPlayer().getName();
        da.timestamp = System.currentTimeMillis();

        try {
            HashMap<String, Object> filter = new HashMap<String, Object>();
            filter.put("player_name", hook.getPlayer().getName());
            Database.get().update(da, filter);
        } catch (DatabaseWriteException e) {
            //Whoops! Derp.
        }
    }
}
