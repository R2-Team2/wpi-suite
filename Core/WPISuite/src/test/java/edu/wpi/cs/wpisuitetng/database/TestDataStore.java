/*******************************************************************************
 * Copyright (c) 2012 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * Robert Smieja
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.db4o.ObjectContainer;
import com.db4o.ObjectServer;
import com.db4o.ObjectSet;
import com.db4o.config.CommonConfiguration;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ClientConfiguration;
import com.db4o.reflect.Reflector;

import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

//TODO: Finish rewrite using mocks
@RunWith(PowerMockRunner.class)
public class TestDataStore {
    ClientConfiguration mockConfig = mock(ClientConfiguration.class);
    CommonConfiguration mockCommonConfig = mock(CommonConfiguration.class);
    ObjectContainer mockDb = mock(ObjectContainer.class);
    ObjectServer mockServer = mock(ObjectServer.class);

    @SuppressWarnings("unchecked") ObjectSet<Object> mockUserSet = mock(ObjectSet.class);

    User user = new User("Ryan", "rchamer", "password", 0);
    Data dataStore = new DataStore();

    @After
    public void cleanup() {
        //Ensure static constant variables will be reset after the one test I replace them with mocks...
        DataStore.theDB.close();
        DataStore.server.close();

        //TODO: Port every test to use mocks and remove this line
        DataStore.theDB = null;
        DataStore.server = null;
        DataStore.instance = null;
    }

    @Test
    @PrepareForTest({ Db4oClientServer.class })
    public void testDelete() throws WPISuiteException {
        DataStore.theDB = mockDb;
        DataStore.server = mockServer;

        when(mockConfig.common()).thenReturn(mockCommonConfig);

        mockStatic(Db4oClientServer.class);

        when(Db4oClientServer.newClientConfiguration()).thenReturn(mockConfig);
        when(mockDb.queryByExample(user)).thenReturn(mockUserSet);
        when(mockUserSet.next()).thenReturn(user);

        assertTrue(dataStore.save(user));

        //Make sure save was successful
        verify(mockCommonConfig, times(1)).reflectWith(any(Reflector.class));
        verify(mockDb, times(1)).store(user);
        verify(mockDb, times(1)).commit();

        assertEquals(user, dataStore.delete(user));

        //Make sure delete ran
        verify(mockCommonConfig, times(2)).reflectWith(any(Reflector.class));
        verify(mockDb, times(1)).delete(user);
        verify(mockDb, times(2)).commit();
    }

    @Test
    public void testSaveandRetrieve() throws WPISuiteException {
        Data db = DataStore.getDataStore();
        User[] arr = new User[1];
        User firstUser = new User("Ryan", "rchamer", "password", 0);
        db.save(firstUser);
        User me = db.retrieve(User.class, "username", "rchamer").toArray(arr)[0];
        assertEquals(me, firstUser);
        db.delete(me);
    }

    @Test
    public void testUpdate() throws WPISuiteException {
        Data db = DataStore.getDataStore();
        User[] arr = new User[2];
        User firstUser = new User("Ryan", "rchamer", "password", 0);
        db.save(firstUser);
        db.update(User.class, "username", "rchamer", "name", "Mjolnir");
        User Mjolnir = db.retrieve(User.class, "username", "rchamer").toArray(arr)[0];
        assertEquals(firstUser, Mjolnir);
        db.delete(Mjolnir);
    }

    @Test
    public void testRetrieveAll() {
        Data db = DataStore.getDataStore();
        User firstUser = new User("Brian", "bgaffey", "password", 0);
        db.save(firstUser);
        User secondUser = new User("Gaffey", "gafftron", "password", 0);
        db.save(secondUser);
        List<User> retrievedList = db.retrieveAll(firstUser);

        int initCount = retrievedList.size();
        assertTrue(retrievedList.contains(firstUser));
        assertTrue(retrievedList.contains(secondUser));

        db.delete(firstUser);
        retrievedList = db.retrieveAll(firstUser);

        assertEquals(initCount - 1, retrievedList.size());
        assertTrue(retrievedList.contains(secondUser));
        assertFalse(retrievedList.contains(firstUser));

        db.deleteAll(firstUser);
    }

    @Test
    public void testDeleteAll() throws WPISuiteException {
        Data db = DataStore.getDataStore();
        User[] arr = new User[2];
        User firstUser = new User("Brian", "bgaffey", "password", 0);
        db.save(firstUser);
        User secondUser = new User("Gaffey", "gafftron", "password", 0);
        db.save(secondUser);
        List<User> retrievedList = db.retrieveAll(firstUser);

        int initCount = retrievedList.size();
        assertTrue(retrievedList.contains(firstUser));
        assertTrue(retrievedList.contains(secondUser));

        retrievedList = db.deleteAll(firstUser);
        User me1 = db.retrieve(User.class, "username", "bgaffey").toArray(arr)[0];
        User me2 = db.retrieve(User.class, "username", "gafftron").toArray(arr)[0];
        assertEquals(initCount, retrievedList.size());
        assertEquals(me1, null);
        assertEquals(me2, null);
    }

    @Test
    public void testRetrieveWithProjects() throws WPISuiteException {
        Data db = DataStore.getDataStore();
        //      User[] arr = new User[2];
        User firstUser = new User("Brian", "bgaffey", "password", 0);
        //      User secondUser = new User("Alex", "alex", "password1", 1);
        Project myProject = new Project("myProject", "0");
        //      Project notMyProject = new Project("notMyProject", "1");
        db.save(firstUser);
        db.save(myProject);
        //      User result = db.retrieve(User.class, "username", "bgaffey", myProject).toArray(arr)[0];
        //  assertEquals(firstUser, result);
        db.deleteAll(firstUser);
        db.deleteAll(myProject);
    }

    @Test
    public void testOrRetrieve() throws WPISuiteException, IllegalAccessException, InvocationTargetException {
        Data db = DataStore.getDataStore();

        //      User[] arr = new User[2];
        User firstUser = new User("Ryan", "rchamer", "password", 0);
        User secondUser = new User("Bryan", "bgaffey", "pword", 1);
        List<User> both = new ArrayList<User>();
        both.add(firstUser);
        both.add(secondUser);
        db.deleteAll(firstUser);
        db.save(firstUser);
        db.save(secondUser);
        String[] list = new String[2];
        list[0] = "Username";
        list[1] = "Name";
        List<Object> objlist = new ArrayList<Object>();
        objlist.add("rchamer");
        objlist.add("Bryan");
        List<Model> me = db.orRetrieve(firstUser.getClass(), list, objlist);
        assertEquals(me, both);
    }

    @Test
    public void testAndRetrieve() throws WPISuiteException, IllegalAccessException, InvocationTargetException {

        Data db = DataStore.getDataStore();

        //      User[] arr = new User[2];
        User firstUser = new User("Ryan", "rchamer", "password", 0);
        User secondUser = new User("Bryan", "rchamer", "pword", 1);
        List<User> first = new ArrayList<User>();
        first.add(firstUser);
        db.deleteAll(firstUser);
        db.save(firstUser);
        db.save(secondUser);
        String[] list = new String[2];
        list[0] = "Username";
        list[1] = "Name";
        List<Object> objlist = new ArrayList<Object>();
        objlist.add("rchamer");
        objlist.add("Ryan");
        List<Model> me = db.andRetrieve(firstUser.getClass(), list, objlist);
        assertEquals(me, first);
    }

    @Test
    public void testComplexRetrieve() throws WPISuiteException, IllegalAccessException, InvocationTargetException {
        Data db = DataStore.getDataStore();

        //      User[] arr = new User[2];
        User firstUser = new User("Ryan", "rchamer", "password", 0);
        User secondUser = new User("Bryan", "rchamer", "pword", 1);
        User thirdUser = new User("Tyler", "twack", "word", 2);
        List<User> first = new ArrayList<User>();
        db.deleteAll(firstUser);
        first.add(firstUser);
        first.add(thirdUser);
        db.deleteAll(firstUser);
        db.save(firstUser);
        db.save(secondUser);
        db.save(thirdUser);
        String[] list = new String[2];
        list[0] = "Username";
        list[1] = "Name";
        List<Object> objlist = new ArrayList<Object>();
        objlist.add("rchamer");
        objlist.add("Ryan");

        String[] orList = new String[2];
        orList[0] = "idNum";
        orList[1] = "Name";
        List<Object> orObjList = new ArrayList<Object>();
        orObjList.add(0);
        orObjList.add("Tyler");

        List<Model> me = db.complexRetrieve(firstUser.getClass(), list, objlist, firstUser.getClass(), orList, orObjList);
        assertEquals(me, first);
    }
}
