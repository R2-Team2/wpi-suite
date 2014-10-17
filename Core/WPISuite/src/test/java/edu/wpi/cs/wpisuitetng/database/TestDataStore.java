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

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.db4o.ObjectContainer;
import com.db4o.ObjectServer;
import com.db4o.ObjectSet;
import com.db4o.config.CommonConfiguration;
import com.db4o.cs.config.ClientConfiguration;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;

//TODO: Finish rewrite using mocks
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

}
