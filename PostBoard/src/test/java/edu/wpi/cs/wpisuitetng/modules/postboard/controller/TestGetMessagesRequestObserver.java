package edu.wpi.cs.wpisuitetng.modules.postboard.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import edu.wpi.cs.wpisuitetng.modules.postboard.model.PostBoardMessage;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

@RunWith(PowerMockRunner.class)
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
    @PrepareForTest(PostBoardMessage.class)
    public void testResponseSuccess() throws Exception {
        mockStatic(PostBoardMessage.class);

        when(mockRequest.getResponse()).thenReturn(mockResponse);
        when(mockResponse.getBody()).thenReturn("Test Response Body");
        when(PostBoardMessage.fromJsonArray("Test Response Body")).thenReturn(mockMessages);

        observer.responseSuccess(mockRequest);

        verify(mockController, times(1)).receivedMessages(mockMessages);
    }

    @Test
    public void testResponseError() {
        observer = spy(observer);

        observer.responseError(mockRequest);

        verify(observer, times(1)).fail(mockRequest, null);
    }

    @Test
    @PrepareForTest({ GetMessagesRequestObserver.class, PostBoardMessage.class })
    public void testFail() throws Exception {
        whenNew(PostBoardMessage.class).withArguments("Error retrieving messages.").thenReturn(mockMessage);

        observer.fail(mockRequest, null);

        verify(mockController, times(1)).receivedMessages(mockMessages);
    }
}
