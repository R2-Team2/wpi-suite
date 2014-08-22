package edu.wpi.cs.wpisuitetng.modules.postboard.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.postboard.model.PostBoardMessage;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

public class TestGetMessagesRequestObserver {
    
    GetMessagesController mockController;
    IRequest mockRequest;
    ResponseModel mockResponse;
    PostBoardMessage[] mockMessages;
    PostBoardMessage mockMessage;
    
    GetMessagesRequestObserver observer;
    
    @Before
    public void setup() {
        mockController = mock(GetMessagesController.class);
        mockRequest = mock(IRequest.class);
        mockResponse = mock(ResponseModel.class);
        mockMessages = new PostBoardMessage[1];
        mockMessage = mock(PostBoardMessage.class);
        
        mockMessages[0] = mockMessage;
        
        observer = new GetMessagesRequestObserver(mockController);
        
    }
    
    @Test
    public void testResponseError() {
        observer = spy(observer);
        
        observer.responseError(mockRequest);
        
        verify(observer, times(1)).fail(mockRequest, null);
    }
    
}
