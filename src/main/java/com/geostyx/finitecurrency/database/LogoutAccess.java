/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geostyx.finitecurrency.database;

/**
 *
 * @author Jacob
 */

import net.canarymod.database.Column;
import net.canarymod.database.DataAccess;

public class LogoutAccess extends WorldAccess {
    
    public LogoutAccess() {
        super("fc_player_logout");
    }

    @Column(columnName = "timestamp", dataType = Column.DataType.LONG)
    public long timestamp;

    @Column(columnName = "player_uuid", dataType = Column.DataType.STRING)
    public String player;
    
    @Column(columnName = "player_name", dataType = Column.DataType.STRING)
    public String playername;
    
    @Override
    public DataAccess getInstance() {
        return new LogoutAccess();
    }
}
