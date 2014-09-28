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
    public long timeStamp;

    @Column(columnName = "player_uuid", dataType = Column.DataType.STRING)
    public String playerUUID;
    
    @Column(columnName = "player_name", dataType = Column.DataType.STRING)
    public String playerName;
    
    @Override
    public DataAccess getInstance() {
        return new LogoutAccess();
    }
}
