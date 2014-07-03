package edu.wpi.cs.wpisuitetng.modules.postboard.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.google.gson.Gson;

/**
 * Unit test example using PowerMock/PowerMockito
 * 
 * @author Robert Smieja
 */

//Unfortunately, we need PowerMockito for GSON
//AVOID POWERMOCK AT ALL COSTS IF POSSIBLE
//If you don't know what you are doing you will cause more harm than good
@RunWith(PowerMockRunner.class)
@PrepareForTest({ Gson.class })
public class TestPostBoardMessage {
    Gson mockParser;
    Date mockDate;
    DateFormat mockDateFormat;
    
    PostBoardMessage message;
    
    @Before
    public void setup() {
        mockParser = mock(Gson.class);
        mockDate = new Date(60L);
        
        mockDateFormat = new SimpleDateFormat("");
        
        PostBoardMessage.setParser(mockParser);
        
        message = new PostBoardMessage("Test Message");
        message.setDate(mockDate);
        message.setDateFormat(mockDateFormat);
    }
    
    @Test
    public void testConstructor() {
        PostBoardMessage newMessage = new PostBoardMessage("Test Message");
        
        assertNotNull(newMessage);
        assertEquals("Test Message", newMessage.getMessage());
        assertNotNull(newMessage.getDate());
        assertNotNull(PostBoardMessage.getParser());
        assertEquals(mockParser, PostBoardMessage.getParser());
    }
    
    @Test
    public void testToString() {
        String result = message.toString();
        
        assertEquals(":    Test Message", result);
    }
    
    @Test
    public void testToJson() {
        when(mockParser.toJson(message, PostBoardMessage.class)).thenReturn("Mock JSON");
        
        String result = message.toJson();
        
        assertEquals("Mock JSON", result);
    }
    
    @Test
    public void testFromJson() {
        when(mockParser.fromJson("Mock JSON", PostBoardMessage.class)).thenReturn(message);
        
        PostBoardMessage result = PostBoardMessage.fromJson("Mock JSON");
        
        assertEquals(message, result);
    }
    
    @Test
    public void testFromJsonArray() {
        when(mockParser.fromJson("Mock JSON", PostBoardMessage[].class)).thenReturn(new PostBoardMessage[] { message });
        
        PostBoardMessage[] result = PostBoardMessage.fromJsonArray("Mock JSON");
        
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
