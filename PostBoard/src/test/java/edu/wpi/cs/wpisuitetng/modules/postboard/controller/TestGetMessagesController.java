package edu.wpi.cs.wpisuitetng.modules.postboard.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.awt.event.ActionEvent;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.postboard.model.PostBoardMessage;
import edu.wpi.cs.wpisuitetng.modules.postboard.model.PostBoardModel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;

public class TestGetMessagesController {
    
    Network mockNetwork;
    Request mockRequest;
    PostBoardModel mockModel;
    ActionEvent mockEvent;
    GetMessagesRequestObserver mockObserver;
    
    PostBoardMessage[] mockMessages;
    
    GetMessagesController controller;
    
    @Before
    public void setup() {
        mockNetwork = mock(Network.class);
        mockRequest = mock(Request.class);
        mockModel = mock(PostBoardModel.class);
        mockEvent = mock(ActionEvent.class);
        mockObserver = mock(GetMessagesRequestObserver.class);
        
        mockMessages = new PostBoardMessage[0];
        
        controller = new GetMessagesController(mockModel);
    }
    
    @Test
    public void testReceivedMessage() {
        controller.receivedMessages(mockMessages);
        
        verify(mockModel, times(1)).emptyModel();
        verify(mockModel, times(1)).addMessages(mockMessages);
    }
    
    @Test
    public void testReceivedMessage_noMessages() {
        controller.receivedMessages(null);
        
        verify(mockModel, times(1)).emptyModel();
        //        verify(mockModel, times(0)).addMessages(null);
    }
}
