package edu.wpi.cs.wpisuitetng.modules.postboard.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class TestPostBoardModel {
    
    PostBoardMessage mockMessage1;
    PostBoardMessage mockMessage2;
    
    PostBoardMessage[] mockMessages;
    
    PostBoardModel postBoardModel;
    
    @Before
    public void setup() {
        mockMessage1 = mock(PostBoardMessage.class);
        mockMessage2 = mock(PostBoardMessage.class);
        
        when(mockMessage1.toString()).thenReturn("Mock Message 1");
        when(mockMessage2.toString()).thenReturn("Mock Message 2");
        
        mockMessages = new PostBoardMessage[2];
        mockMessages[0] = mockMessage1;
        mockMessages[1] = mockMessage2;
        
        postBoardModel = new PostBoardModel();
    }
    
    @Test
    public void testConstructor() {
        assertNotNull(postBoardModel.getMessages());
        assertEquals(0, postBoardModel.getMessages().size());
        assertEquals(0, postBoardModel.getSize());
    }
    
    @Test
    public void testAddMessage() {
        postBoardModel.addMessage(mockMessage1);
        
        assertNotNull(postBoardModel.getMessages());
        assertEquals(1, postBoardModel.getMessages().size());
        assertEquals(1, postBoardModel.getSize());
        assertTrue(postBoardModel.getMessages().contains(mockMessage1));
    }
    
    @Test
    public void testAddMessages() {
        postBoardModel.addMessages(mockMessages);
        
        assertNotNull(postBoardModel.getMessages());
        assertEquals(2, postBoardModel.getMessages().size());
        assertEquals(2, postBoardModel.getSize());
        assertEquals(mockMessage1, postBoardModel.getMessages().get(0));
        assertEquals(mockMessage2, postBoardModel.getMessages().get(1));
        assertEquals("Mock Message 2", postBoardModel.getElementAt(0));
        assertEquals("Mock Message 1", postBoardModel.getElementAt(1));
    }
    
    @Test
    public void testEmptyModel() {
        postBoardModel.addMessages(mockMessages);
        
        assertNotNull(postBoardModel.getMessages());
        assertEquals(2, postBoardModel.getMessages().size());
        assertEquals(2, postBoardModel.getSize());
        assertEquals(mockMessage1, postBoardModel.getMessages().get(0));
        assertEquals(mockMessage2, postBoardModel.getMessages().get(1));
        
        postBoardModel.emptyModel();
        
        assertNotNull(postBoardModel.getMessages());
        assertEquals(0, postBoardModel.getMessages().size());
        assertEquals(0, postBoardModel.getSize());
    }
    
    @Test
    public void testSerialVersionUID() {
        assertEquals(-8385236147593098321L, PostBoardModel.getSerialversionuid());
    }
}
