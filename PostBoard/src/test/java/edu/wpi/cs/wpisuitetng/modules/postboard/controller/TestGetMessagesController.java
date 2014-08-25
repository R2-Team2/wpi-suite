package edu.wpi.cs.wpisuitetng.modules.postboard.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.event.ActionEvent;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import edu.wpi.cs.wpisuitetng.modules.postboard.model.PostBoardMessage;
import edu.wpi.cs.wpisuitetng.modules.postboard.model.PostBoardModel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class TestGetMessagesController {
    
    Network mockNetwork = mock(Network.class);
    Request mockRequest = mock(Request.class);
    PostBoardModel mockModel = mock(PostBoardModel.class);
    ActionEvent mockEvent = mock(ActionEvent.class);
    
    PostBoardMessage[] messages = new PostBoardMessage[0];
    
    GetMessagesController controller = new GetMessagesController(mockModel);
    
    @Test
    public void testActionPreformed() throws Exception {
        ArgumentCaptor<GetMessagesRequestObserver> observerCaptor = ArgumentCaptor.forClass(GetMessagesRequestObserver.class);
        Network.setInstance(mockNetwork);
        
        when(mockNetwork.makeRequest("postboard/postboardmessage", HttpMethod.GET)).thenReturn(mockRequest);
        
        controller.actionPerformed(mockEvent);
        
        verify(mockRequest, times(1)).addObserver(observerCaptor.capture());
        verify(mockRequest, times(1)).send();
        
        assertEquals(controller, observerCaptor.getValue().getController());
    }
    
    @Test
    public void testReceivedMessage() {
        controller.receivedMessages(messages);
        
        verify(mockModel, times(1)).emptyModel();
        verify(mockModel, times(1)).addMessages(messages);
    }
    
    @Test
    public void testReceivedMessage_noMessages() {
        controller.receivedMessages(null);
        
        verify(mockModel, times(1)).emptyModel();
        //        verify(mockModel, times(0)).addMessages(null);
    }
}
