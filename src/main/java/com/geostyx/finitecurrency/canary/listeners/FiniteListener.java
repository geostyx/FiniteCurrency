/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geostyx.finitecurrency.canary.listeners;
/**
 *
 * @author Jacob
 */
// import net.canarymod.hook.Hook;
import com.geostyx.finitecurrency.FiniteCurrency;
import com.geostyx.finitecurrency.database.LogoutAccess;
import java.util.HashMap;
import net.canarymod.Canary;
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
        FiniteCurrency.getLogger().info("Player Joined: " + hook.getPlayer().getName());
        hook.getPlayer().message(Colors.YELLOW + "Hello World, " + hook.getPlayer().getName());

        LogoutAccess da = new LogoutAccess();
        try {
            //Read data and put it into our (currently empty) dataaccess instance
            // Database.get().load(da, new String[] {{"player_name"}, hook.getPlayer().getName()});
            HashMap<String, Object> filter = new HashMap<>();
            filter.put("player_uuid", hook.getPlayer().getUUIDString());
            Database.get().load(da, filter);
        } catch (DatabaseReadException e) {
            //Whoops! Derp.
        }
        hook.getPlayer().message("HI!!!! HOW ARE YOU BRO???233");
        if (da.hasData()) {
            hook.getPlayer().message(String.valueOf(System.currentTimeMillis()) + " | " + String.valueOf(da.timestamp));
            hook.getPlayer().message("Name based on UUID: " + Colors.GREEN + Canary.getServer().getPlayerFromUUID(da.player) + " | String name: " + da.playername);

            hook.getPlayer().message("Your last login was " + ((System.currentTimeMillis() - da.timestamp) / 1000L / 60 / 60) + " hours ago.");
        } else {
            hook.getPlayer().message("Welcome to our Server!");
        }

    }

    @HookHandler
    public void logout(DisconnectionHook hook) {
        LogoutAccess da = new LogoutAccess();
        da.player = hook.getPlayer().getUUIDString();
        da.timestamp = System.currentTimeMillis();
        da.playername = hook.getPlayer().getName();
        da.world = hook.getPlayer().getWorld().getFqName();
        FiniteCurrency.getLogger().info("da.playername: " + da.playername);
        try {
            HashMap<String, Object> filter = new HashMap<>();
            filter.put("player_uuid", hook.getPlayer().getUUIDString());
            Database.get().load(da, filter);
            if (da.hasData()) {
                Database.get().update(da, filter);
                FiniteCurrency.getLogger().info("UPDATED YAY");
            } else {
                Database.get().insert(da);
            }
            
        } catch (DatabaseWriteException e) {
            //Whoops! Derp.
            FiniteCurrency.getLogger().info(e.getMessage());
        } catch (DatabaseReadException e) {

        }
    }
}
