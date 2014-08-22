package edu.wpi.cs.wpisuitetng.modules.postboard.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import edu.wpi.cs.wpisuitetng.modules.postboard.model.PostBoardMessage;
import edu.wpi.cs.wpisuitetng.modules.postboard.model.PostBoardModel;
import edu.wpi.cs.wpisuitetng.modules.postboard.view.BoardPanel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({ "javax.swing.*" })
public class TestAddMessageController {
    
    PostBoardModel mockModel;
    BoardPanel mockView;
    
    Network mockNetwork;
    Request mockRequest;
    AddMessageRequestObserver mockObserver;
    
    ActionEvent mockActionEvent;
    JTextField mockTextField;
    PostBoardMessage mockMessage;
    
    AddMessageController controller;
    
    @Before
    public void setup() {
        mockModel = mock(PostBoardModel.class);
        mockView = mock(BoardPanel.class);
        
        mockNetwork = mock(Network.class);
        mockRequest = mock(Request.class);
        mockObserver = mock(AddMessageRequestObserver.class);
        
        mockTextField = mock(JTextField.class);
        mockActionEvent = mock(ActionEvent.class);
        
        mockMessage = mock(PostBoardMessage.class);
        
        controller = new AddMessageController(mockModel, mockView);
    }
    
    @Test
    @PrepareForTest({ Network.class, PostBoardMessage.class, AddMessageRequestObserver.class, AddMessageController.class })
    public void testActionPerformed() throws Exception {
        mockStatic(Network.class);
        mockStatic(PostBoardMessage.class);
        mockStatic(AddMessageRequestObserver.class);
        
        when(mockView.getTxtNewMessage()).thenReturn(mockTextField);
        when(mockTextField.getText()).thenReturn("Test Message");
        when(mockNetwork.makeRequest("postboard/postboardmessage", HttpMethod.PUT)).thenReturn(mockRequest);
        when(Network.getInstance()).thenReturn(mockNetwork);
        when(mockMessage.toJson()).thenReturn("Test Json");
        
        whenNew(PostBoardMessage.class).withArguments("Test Message").thenReturn(mockMessage);
        whenNew(AddMessageRequestObserver.class).withArguments(controller).thenReturn(mockObserver);
        
        controller.actionPerformed(mockActionEvent);
        
        verify(mockView, times(2)).getTxtNewMessage();
        verify(mockTextField, times(1)).setText("");
        
        verify(mockRequest, times(1)).setBody("Test Json");
        verify(mockRequest, times(1)).addObserver(mockObserver);
        verify(mockRequest, times(1)).send();
    }
    
    @Test
    public void testActionPerformed_EmptyMessage() throws Exception {
        mockStatic(Network.class);
        when(mockView.getTxtNewMessage()).thenReturn(mockTextField);
        when(mockTextField.getText()).thenReturn("");
        
        controller.actionPerformed(mockActionEvent);
        
        verify(mockView, times(1)).getTxtNewMessage();
        verify(mockTextField, times(0)).setText("");
    }
    
    @Test
    public void testAddMessage() {
        controller.addMessageToModel(mockMessage);
        
        verify(mockModel, times(1)).addMessage(mockMessage);
    }
}
