package edu.wpi.cs.wpisuitetng.modules.postboard.model;

import static edu.wpi.cs.wpisuitetng.test.util.TestHelpers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;

public class TestPostBoardEntityManager {
    Data mockDb;
    Session mockSession;
    Project mockProject;
    
    Gson mockParser;
    
    PostBoardMessage mockMessage1;
    PostBoardMessage mockMessage2;
    List<PostBoardMessage> mockMessageList;
    List<Model> mockModelList;
    
    PostBoardEntityManager entityManager;
    
    @Before
    public void setup() {
        mockDb = mock(Data.class);
        mockSession = mock(Session.class);
        mockProject = mock(Project.class);
        
        mockMessage1 = mock(PostBoardMessage.class);
        mockMessage2 = mock(PostBoardMessage.class);
        
        mockMessageList = new ArrayList<PostBoardMessage>();
        mockMessageList.add(mockMessage1);
        mockMessageList.add(mockMessage2);
        
        mockModelList = new ArrayList<Model>(mockMessageList);
        
        entityManager = new PostBoardEntityManager(mockDb);
        
        when(mockSession.getProject()).thenReturn(mockProject);
    }
    
    @Test
    public void testConstructor() {
        assertEquals(mockDb, entityManager.getDb());
    }
    
    @Test(expected = WPISuiteException.class)
    public void testGetEntity() throws WPISuiteException {
        entityManager.getEntity(mockSession, "Test ID");
    }
    
    @Test
    public void testGetAll() throws WPISuiteException {
        when(mockDb.retrieveAll(any(PostBoardMessage.class), eq(mockProject))).thenReturn(mockModelList);
        
        PostBoardMessage[] result = entityManager.getAll(mockSession);
        
        assertEquals(2, result.length);
        assertTrue(contains(result, mockMessage1));
        assertTrue(contains(result, mockMessage2));
    }
    
    @Test(expected = WPISuiteException.class)
    public void testUpdate() throws WPISuiteException {
        entityManager.update(mockSession, "Test Update Content");
    }
    
    @Test
    public void testSave() throws WPISuiteException {
        entityManager.save(mockSession, mockMessage1);
        
        verify(mockDb, times(1)).save(mockMessage1);
    }
    
    @Test(expected = WPISuiteException.class)
    public void testDeleteEntity() throws WPISuiteException {
        entityManager.deleteEntity(mockSession, "Test ID");
    }
    
    @Test(expected = WPISuiteException.class)
    public void testDeleteAll() throws WPISuiteException {
        entityManager.deleteAll(mockSession);
    }
    
    @Test
    public void testCount() throws WPISuiteException {
        when(mockDb.retrieveAll(any(PostBoardMessage.class))).thenReturn(mockMessageList);
        
        int count = entityManager.Count();
        
        assertEquals(2, count);
        
        verify(mockDb, times(1)).retrieveAll(any(PostBoardMessage.class));
    }
    
    @Test
    public void testAdvancedGet() throws WPISuiteException {
        assertNull(entityManager.advancedGet(mockSession, new String[] { "Test String 1", "Test String 2" }));
    }
    
    @Test
    public void testAdvancedPut() throws WPISuiteException {
        assertNull(entityManager.advancedPut(mockSession, new String[] { "Test Arg1", "Test Arg2" }, "Test Content"));
    }
    
    @Test
    public void testAdvancedPost() throws WPISuiteException {
        assertNull(entityManager.advancedPost(mockSession, "Test String", "Test Content"));
    }
}
