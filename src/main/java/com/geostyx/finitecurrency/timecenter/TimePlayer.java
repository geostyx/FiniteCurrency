/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geostyx.finitecurrency.timecenter;
import com.geostyx.finitecurrency.FiniteCurrency;
import com.geostyx.finitecurrency.Saveable;
import com.geostyx.finitecurrency.database.TimePlayerAccess;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import net.canarymod.database.Database;
import net.canarymod.database.exceptions.DatabaseReadException;
import net.canarymod.database.exceptions.DatabaseWriteException;
/**
 *
 * @author GeoStyx
 */
public class TimePlayer extends Saveable{
    private final String uuid;
    private String name;
    private String world;
    private long lastLogin;
    
    public TimePlayer(String uuid){
        this.uuid = uuid;
    }
    
    /**
     * This will set the lastLogin of the player to specified time in seconds.
     * 
     * @param lastLogin Last login time in seconds 
     */
    public void setLastLogin(long lastLogin){
        this.lastLogin = lastLogin;
    }
    
    /**
     * This will set the lastLogin of the player to the current time in seconds.
     */
    public void setLastLogin(){
        this.lastLogin = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
    }
    /**
     * Load player from the DB
     */
    @Override
    public void load(){
        TimePlayerAccess data = new TimePlayerAccess();
        try{
            HashMap<String, Object> filter = new HashMap<>();
            filter.put("uuid", this.uuid);
            Database.get().load(data,filter);
        }catch(DatabaseReadException e){
            FiniteCurrency.getLogger().error("Error reading player data", e);
        }
        this.name = data.name;
        this.world = data.world;
        this.lastLogin = data.lastLogin;
    }
    
    /**
     * Save the player to the DB
     */
    @Override
    public void save(){
        TimePlayerAccess data = new TimePlayerAccess();
        data.uuid = this.uuid;
        data.name = this.name;
        data.world = this.world;
        data.lastLogin = this.lastLogin;
        try{
            HashMap<String, Object> filter = new HashMap<>();
            filter.put("uuid", this.uuid);
            Database.get().update(data, filter);
        }catch(DatabaseWriteException e){
            FiniteCurrency.getLogger().error("Error saving FinitePlayer data", e);
        }
    }
}
