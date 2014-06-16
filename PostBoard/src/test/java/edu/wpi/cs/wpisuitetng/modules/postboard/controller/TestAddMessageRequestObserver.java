package edu.wpi.cs.wpisuitetng.modules.postboard.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import edu.wpi.cs.wpisuitetng.modules.postboard.model.PostBoardMessage;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

@RunWith(PowerMockRunner.class)
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
    @PrepareForTest(PostBoardMessage.class)
    public void testResponseSuccess() {
        mockStatic(PostBoardMessage.class);
        
        when(mockRequest.getResponse()).thenReturn(mockResponse);
        when(mockResponse.getBody()).thenReturn("Test Response Body");
        when(PostBoardMessage.fromJson("Test Response Body")).thenReturn(mockMessage);
        
        observer.responseSuccess(mockRequest);
        
        verify(mockController, times(1)).addMessageToModel(mockMessage);
    }
    
    @Test
    public void testResponseError() {
        final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));
        
        observer.responseError(mockRequest);
        
        System.setErr(null);
        assertEquals("The request to add a message failed.\r\n", errContent.toString());
    }
    
    @Test
    public void testFail() {
        final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));
        
        observer.fail(mockRequest, null);
        
        System.setErr(null);
        assertEquals("The request to add a message failed.\r\n", errContent.toString());
    }
    
}
