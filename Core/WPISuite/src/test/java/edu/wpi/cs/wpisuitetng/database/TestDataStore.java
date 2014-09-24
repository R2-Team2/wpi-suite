/*******************************************************************************
 * Copyright (c) 2014 -- WPI Suite
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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import org.junit.Before;
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
    DataStore dataStore = new DataStore();

    @Before
    public void setup() {
        DataStore.instance = dataStore;
    }

    @Test
    public void testConstructor() {
        assertNotNull(dataStore);
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
}
