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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;

import com.db4o.ObjectContainer;
import com.db4o.ObjectServer;
import com.db4o.ObjectSet;
import com.db4o.config.CommonConfiguration;
import com.db4o.cs.config.ClientConfiguration;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;

//TODO: Finish rewrite using mocks
public class TestDataStore {
    ClientConfiguration mockConfig;
    CommonConfiguration mockCommonConfig;
    ObjectContainer mockDb;
    ObjectServer mockServer;
    ObjectSet<Object> mockUserSet;
    
    User mockUser;
    
    Data dataStore;
    
    @SuppressWarnings("unchecked")
    @Before
    public void setup() {
        mockConfig = mock(ClientConfiguration.class);
        mockCommonConfig = mock(CommonConfiguration.class);
        mockDb = mock(ObjectContainer.class);
        mockServer = mock(ObjectServer.class);
        
        mockUser = new User("Ryan", "rchamer", "password", 0);
        mockUserSet = mock(ObjectSet.class);
        dataStore = new DataStore();
        
        DataStore.theDB = mockDb;
        DataStore.server = mockServer;
        
        when(mockConfig.common()).thenReturn(mockCommonConfig);
    }
    
    @After
    public void cleanup() {
        //Ensure static constant variables will be reset after the one test I replace them with mocks...
        //TODO: Port every test to use mocks and remove this line
        DataStore.theDB = null;
        DataStore.server = null;
    }
}