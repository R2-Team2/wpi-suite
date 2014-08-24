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
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;

public class TestPostBoardEntityManager {
    private Data mockDb = mock(Data.class);
    private Session mockSession = mock(Session.class);
    private Project mockProject = mock(Project.class);
    
    private PostBoardMessage message = new PostBoardMessage("Test");
    private Date messageDate = new Date(0L);
    private String messageDateJson = new Gson().toJson(messageDate);
    private PostBoardMessage mockMessage1 = mock(PostBoardMessage.class);
    private PostBoardMessage mockMessage2 = mock(PostBoardMessage.class);
    private List<PostBoardMessage> mockMessageList = new ArrayList<PostBoardMessage>();
    
    private PostBoardEntityManager entityManager = new PostBoardEntityManager(mockDb);
    
    @Before
    public void setup() {
        message.setDate(messageDate);
        
        mockMessageList.add(mockMessage1);
        mockMessageList.add(mockMessage2);
        
        when(mockSession.getProject()).thenReturn(mockProject);
    }
    
    @Test
    public void testConstructor() {
        assertEquals(mockDb, entityManager.getDb());
    }
    
    @Test
    public void testMakeEntity() throws WPISuiteException {
        ArgumentCaptor<PostBoardMessage> messageCaptor = ArgumentCaptor.forClass(PostBoardMessage.class);
        String messageJson = "{\"message\":\"Test\",\"date\":" + messageDateJson + "}";
        
        when(mockDb.save(messageCaptor.capture(), eq(mockProject))).thenReturn(true);
        
        PostBoardMessage result = entityManager.makeEntity(mockSession, messageJson);
        
        assertEquals(message, result);
        assertEquals(message, messageCaptor.getValue());
        
        verify(mockDb, times(1)).save(message, mockProject);
    }
    
    @Test(expected = WPISuiteException.class)
    //
    public void testMakeEntity_Exception() throws WPISuiteException {
        ArgumentCaptor<PostBoardMessage> messageCaptor = ArgumentCaptor.forClass(PostBoardMessage.class);
        String messageJson = "{\"message\":\"Test\",\"date\":" + messageDateJson + "}";
        
        when(mockDb.save(messageCaptor.capture(), eq(mockProject))).thenReturn(false);
        
        PostBoardMessage result = entityManager.makeEntity(mockSession, messageJson);
        
        verify(mockDb, times(1)).save(mockMessage1, mockProject);
        
        assertNull(result);
    }
    
    @Test(expected = WPISuiteException.class)
    public void testGetEntity() throws WPISuiteException {
        entityManager.getEntity(mockSession, "Test ID");
    }
    
    @Test
    public void testGetAll() throws WPISuiteException {
        List<Model> mockModelList = new ArrayList<Model>(mockMessageList);
        
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
