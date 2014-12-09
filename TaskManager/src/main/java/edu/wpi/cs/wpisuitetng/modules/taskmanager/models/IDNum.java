/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.models;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.modules.AbstractModel;


/**
 * IDNum class for storing ID counter.
 *
 * @version $Revision: 1.0 $
 * @author R2-Team2
 */
public class IDNum extends AbstractModel {

    private long Num;
    private static Data db = null;
    private final int id;


    /**
     * IDNum object should be instantiated only if it doesn't already exist in database. If it is
     * instantiated, it will be initialized with a counter value of 0.
     *
     * @param db the database
     */
    public IDNum(Data db) {
        id = 0;
        Num = 0;
        if (IDNum.db == null) {
            IDNum.db = db;
        }
    }

    public long getNum() {
        return Num;
    }

    @Override
    public void save() {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete() {
        // TODO Auto-generated method stub

    }

    @Override
    public String toJson() {
        return new Gson().toJson(this, IDNum.class);
    }

    @Override
    public Boolean identify(Object o) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return long next available ID number
     */
    public long getAndIncID() {
        final long oldIDNum = Num;
        Num += 1;

        IDNum.db.save(this);
        // IDNum.db.update(IDNum.class, "id", 0, "Num", this.Num);
        return oldIDNum;
    }

}
