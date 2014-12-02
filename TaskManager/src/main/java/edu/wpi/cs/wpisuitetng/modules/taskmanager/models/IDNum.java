package edu.wpi.cs.wpisuitetng.modules.taskmanager.models;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;

public class IDNum extends AbstractModel {

    static long IDNum;

    /**
     * IDNum object should be instantiated only if it doesn't already exist in database. If it is
     * instantiated, it will be initialized with a counter value of 0.
     */
    public IDNum() {
        IDNum = 0;
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
        long oldIDNum = IDNum;
        IDNum = IDNum + 1;
        return oldIDNum;
    }

}
