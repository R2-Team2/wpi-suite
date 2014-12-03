package edu.wpi.cs.wpisuitetng.modules.taskmanager.models;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.AbstractModel;

public class IDNum extends AbstractModel {

    private long Num;
    private static Data db;
    private int id;
    

    /**
     * IDNum object should be instantiated only if it doesn't already exist in database. If it is
     * instantiated, it will be initialized with a counter value of 0.
     */
    public IDNum(Data db) {
        id = 0;
    	Num = 0;
    	IDNum.db = db;
    }

    public long getNum(){
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
     * @throws WPISuiteException 
     */
    public long getAndIncID() throws WPISuiteException {
        long oldIDNum = Num;
        this.Num = Num + 1;
   
        IDNum.db.save(this);
        //IDNum.db.update(IDNum.class, "id", 0, "Num", this.Num);
        return oldIDNum;
    }

}
