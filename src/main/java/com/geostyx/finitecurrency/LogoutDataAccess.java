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

import net.canarymod.database.Column;
import net.canarymod.database.Column.DataType;
import net.canarymod.database.DataAccess;

public class LogoutDataAccess extends DataAccess {
    
    public LogoutDataAccess() {
        super("player_last_logout");
    }

    @Column(columnName = "timestamp", dataType = DataType.LONG)
    public long timestamp;

    @Column(columnName = "player_name", dataType = DataType.STRING)
    public String player;
    
    @Override
    public DataAccess getInstance() {
        return new LogoutDataAccess();
    }
}
