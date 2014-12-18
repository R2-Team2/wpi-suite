package edu.wpi.cs.wpisuitetng.modules.taskmanager.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.database.Data;

public class IDNumTest {

    IDNum idNum;
    Data db;

    @Before
    public void setUp() throws Exception {
        idNum = new IDNum(db);
    }

    @Test
    public void testIDNumConstructor() {
        assertNotNull(idNum);
    }

    @Test
    public void testGetNum() {
        assertEquals(idNum.getNum(), 0);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSave() {
        idNum.save();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDelete() {
        idNum.delete();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIdentify() {
        idNum.identify(new IDNum(db));
    }

    @Test
    public void testGetAndIncID() {
        assertEquals(idNum.getNum(), 0);
        assertEquals(idNum.getAndIncID(), 0);
        assertEquals(idNum.getNum(), 1);
    }

}
