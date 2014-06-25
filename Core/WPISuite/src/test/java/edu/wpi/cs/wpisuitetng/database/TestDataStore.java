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
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.After;
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
	ClientConfiguration mockConfig;
	CommonConfiguration mockCommonConfig;
	ObjectContainer mockDb;
	ObjectServer mockServer;
	ObjectSet<Object> mockUserSet;
	
	User mockUser;
	
	Data dataStore;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setup(){
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
	public void cleanup(){
		//Ensure static constant variables will be reset after the one test I replace them with mocks...
		//TODO: Port every test to use mocks and remove this line
		DataStore.theDB = null;
		DataStore.server = null;
	}
	
	@Test
	@PrepareForTest({Db4oClientServer.class})
	public void testDelete() throws WPISuiteException{
		mockStatic(Db4oClientServer.class);
		
		when(Db4oClientServer.newClientConfiguration()).thenReturn(mockConfig);
		when(mockDb.queryByExample(mockUser)).thenReturn(mockUserSet);
		when(mockUserSet.next()).thenReturn(mockUser);
		
		assertTrue(dataStore.save(mockUser));
		
		//Make sure save was successful
		verify(mockCommonConfig, times(1)).reflectWith(any(Reflector.class));
		verify(mockDb, times(1)).store(mockUser);
		verify(mockDb, times(1)).commit();
		
		assertEquals(mockUser, dataStore.delete(mockUser));
		
		//Make sure delete ran
		verify(mockCommonConfig, times(2)).reflectWith(any(Reflector.class));
		verify(mockDb, times(1)).delete(mockUser);
		verify(mockDb, times(2)).commit();
	}
}
