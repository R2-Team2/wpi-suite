package edu.wpi.cs.wpisuitetng.modules.postboard.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import java.awt.event.ActionEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import edu.wpi.cs.wpisuitetng.modules.postboard.model.PostBoardMessage;
import edu.wpi.cs.wpisuitetng.modules.postboard.model.PostBoardModel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

@RunWith(PowerMockRunner.class)
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
    @PrepareForTest({ Network.class, GetMessagesRequestObserver.class, GetMessagesController.class })
    public void testActionPreformed() throws Exception {
        mockStatic(Network.class);
        mockStatic(GetMessagesRequestObserver.class);
        
        when(Network.getInstance()).thenReturn(mockNetwork);
        when(mockNetwork.makeRequest("postboard/postboardmessage", HttpMethod.GET)).thenReturn(mockRequest);
        
        whenNew(GetMessagesRequestObserver.class).withArguments(controller).thenReturn(mockObserver);
        
        controller.actionPerformed(mockEvent);
        
        verify(mockRequest, times(1)).addObserver(mockObserver);
        verify(mockRequest, times(1)).send();
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
        verify(mockModel, times(0)).addMessages(null);
    }
}
