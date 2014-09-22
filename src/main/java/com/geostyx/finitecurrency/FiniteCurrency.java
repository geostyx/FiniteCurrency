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

import net.canarymod.Canary;
import net.canarymod.logger.Logman;
import net.canarymod.plugin.Plugin;

public class FiniteCurrency extends Plugin{
    public static Logman logger;
    
    public FiniteCurrency(){
        
            FiniteCurrency.logger = getLogman();
    }
    
    @Override
    public boolean enable() {
        
        Canary.hooks().registerListener(new FiniteListener(), this);
        logger.info("Enabling "+getName() + " Version " + getVersion());
        logger.info("Authored by "+getAuthor());
        return true;
    }
    
    @Override
    public void disable() {
        // TODO Auto-generated method stub
    }
}
