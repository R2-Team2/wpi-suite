package edu.wpi.cs.wpisuitetng.modules.postboard.controller;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import edu.wpi.cs.wpisuitetng.modules.postboard.model.PostBoardMessage;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

public class TestGetMessagesRequestObserver {

    GetMessagesController mockController = mock(GetMessagesController.class);
    IRequest mockRequest = mock(IRequest.class);
    ResponseModel mockResponse = mock(ResponseModel.class);

    PostBoardMessage[] messages = new PostBoardMessage[1];
    PostBoardMessage message = new PostBoardMessage("Test Message");

    GetMessagesRequestObserver observer = new GetMessagesRequestObserver(mockController);

    @Before
    public void setup() {
        message.setDate(new Date(0L));
        messages[0] = message;
    }

    @Test
    public void testResponseSuccess() throws Exception {
        ArgumentCaptor<PostBoardMessage[]> messagesCaptor = ArgumentCaptor.forClass(PostBoardMessage[].class);
        when(mockRequest.getResponse()).thenReturn(mockResponse);
        when(mockResponse.getBody()).thenReturn("[{\"message\":\"Test Message\",\"date\":\"Dec 31, 1969 7:00:00 PM\",\"permissionMap\":{}}]");

        observer.responseSuccess(mockRequest);

        verify(mockController, times(1)).receivedMessages(messagesCaptor.capture());

        assertArrayEquals(messages, messagesCaptor.getValue());
    }

    @Test
    public void testResponseError() {
        observer = spy(observer);

        observer.responseError(mockRequest);

        verify(observer, times(1)).fail(mockRequest, null);
    }

    @Test
    public void testFail() throws Exception {
        ArgumentCaptor<PostBoardMessage[]> messagesCaptor = ArgumentCaptor.forClass(PostBoardMessage[].class);

        observer.fail(mockRequest, null);

        verify(mockController, times(1)).receivedMessages(messagesCaptor.capture());

        PostBoardMessage[] errorMessages = messagesCaptor.getValue();
        assertEquals(1, errorMessages.length);

        PostBoardMessage errorMessage = errorMessages[0];
        assertEquals("Error retrieving messages.", errorMessage.getMessage());
        assertNotNull(errorMessage.getDate()); //We can't verify the exact date, but we can verify that one exists
    }
}
