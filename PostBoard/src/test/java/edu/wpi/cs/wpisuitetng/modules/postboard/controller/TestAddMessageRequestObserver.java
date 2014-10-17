package edu.wpi.cs.wpisuitetng.modules.postboard.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.postboard.model.PostBoardMessage;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

public class TestAddMessageRequestObserver {
    private AddMessageController mockController = mock(AddMessageController.class);
    private IRequest mockRequest = mock(IRequest.class);
    private ResponseModel mockResponse = mock(ResponseModel.class);
    private AddMessageRequestObserver observer = new AddMessageRequestObserver(mockController);
    
    @Test
    public void testResponseSuccess() {
        PostBoardMessage message = new PostBoardMessage("Test");
        message.setDate(new Date(0L));
        String messageDateJson = new Gson().toJson(new Date(0L));
        
        ArgumentCaptor<PostBoardMessage> messageCaptor = ArgumentCaptor.forClass(PostBoardMessage.class);
        
        when(mockRequest.getResponse()).thenReturn(mockResponse);
        when(mockResponse.getBody()).thenReturn("{\"message\":\"Test\",\"date\":" + messageDateJson + "}");
        
        observer.responseSuccess(mockRequest);
        
        verify(mockController, times(1)).addMessageToModel(messageCaptor.capture());
        
        assertEquals(message, messageCaptor.getValue());
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
