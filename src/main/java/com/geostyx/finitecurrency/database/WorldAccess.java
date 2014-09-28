/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geostyx.finitecurrency.database;

import net.canarymod.database.Column;
import net.canarymod.database.DataAccess;

/**
 * This is a common { @Link DataAccess } for other { @Link DataAccess } to
 * extend. This contains fields for identifying the world which they affect.
 * @author GeoStyx
 */
public abstract class WorldAccess extends DataAccess {
    public WorldAccess(String tableName){
        super(tableName);
    }
    
    @Column(columnName = "fc_world", dataType = Column.DataType.STRING)
    public String world;
    
    
}
