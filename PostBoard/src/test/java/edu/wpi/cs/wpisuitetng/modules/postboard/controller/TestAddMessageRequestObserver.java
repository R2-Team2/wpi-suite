package edu.wpi.cs.wpisuitetng.modules.postboard.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.postboard.model.PostBoardMessage;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

public class TestAddMessageRequestObserver {
    
    AddMessageController mockController;
    IRequest mockRequest;
    ResponseModel mockResponse;
    PostBoardMessage mockMessage;
    
    AddMessageRequestObserver observer;
    
    @Before
    public void setup() {
        mockController = mock(AddMessageController.class);
        mockRequest = mock(IRequest.class);
        mockResponse = mock(ResponseModel.class);
        mockMessage = mock(PostBoardMessage.class);
        
        observer = new AddMessageRequestObserver(mockController);
    }
    
    @Test
    public void testResponseError() {
        final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));
        
        observer.responseError(mockRequest);
        
        System.setErr(null);
        assertEquals("The request to add a message failed." + System.getProperty("line.separator"), errContent.toString());
    }
    
    @Test
    public void testFail() {
        final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));
        
        observer.fail(mockRequest, null);
        
        System.setErr(null);
        assertEquals("The request to add a message failed." + System.getProperty("line.separator"), errContent.toString());
    }
    
}
