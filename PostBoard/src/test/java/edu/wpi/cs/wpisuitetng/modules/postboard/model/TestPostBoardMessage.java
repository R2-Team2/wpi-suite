package edu.wpi.cs.wpisuitetng.modules.postboard.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test example
 * 
 * @author Robert Smieja
 */

public class TestPostBoardMessage {
    Date date = new Date(0L);
    
    PostBoardMessage message = new PostBoardMessage("Test Message");
    
    @Before
    public void setup() throws Exception {
        message.setDate(date);
    }
    
    @Test
    public void testConstructor() {
        PostBoardMessage newMessage = new PostBoardMessage("Test Message");
        
        assertNotNull(newMessage);
        assertEquals("Test Message", newMessage.getMessage());
        assertNotNull(newMessage.getDate());
    }
    
    @Test
    public void testToString() {
        String result = message.toString();
        
        assertEquals("12/31/69 07:00 PM:    Test Message", result);
    }
    
    @Test
    public void testToJson() {
        String result = message.toJson();
        
        assertEquals("{\"message\":\"Test Message\",\"date\":\"Dec 31, 1969 7:00:00 PM\",\"permissionMap\":{}}", result);
    }
    
    @Test
    public void testFromJson() {
        PostBoardMessage result = PostBoardMessage.fromJson("{\"message\":\"Test Message\",\"date\":\"Dec 31, 1969 7:00:00 PM\",\"permissionMap\":{}}");
        
        assertEquals(message, result);
    }
    
    @Test
    public void testFromJsonArray() {
        PostBoardMessage[] result = PostBoardMessage.fromJsonArray("[{\"message\":\"Test Message\",\"date\":\"Dec 31, 1969 7:00:00 PM\",\"permissionMap\":{}}]");
        
        assertEquals(1, result.length);
        assertEquals(message, result[0]);
    }
    
    @Test
    public void testIdentify() {
        assertNull(message.identify(null));
    }
    
    @Test
    public void testSaveAndDelete() {
        //This gives more code coverage! 
        message.save();
        message.delete();
        
        assertNotNull(message);
        assertEquals("Test Message", message.getMessage());
        assertNotNull(message.getDate());
    }
}
