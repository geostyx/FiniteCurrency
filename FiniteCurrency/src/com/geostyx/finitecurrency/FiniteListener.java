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
import net.canarymod.chat.Colors;
import net.canarymod.hook.HookHandler;
import net.canarymod.hook.player.ConnectionHook;
import net.canarymod.plugin.PluginListener;

public class FiniteListener implements PluginListener{
    @HookHandler
    public void onLogin(ConnectionHook hook){
        FiniteCurrency.logger.info("Player Joined: "+hook.getPlayer().getName());
        hook.getPlayer().message(Colors.YELLOW+"Hello World, "+hook.getPlayer().getName());
        
    }
}
