package edu.wpi.cs.wpisuitetng.modules.postboard.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.postboard.model.PostBoardMessage;
import edu.wpi.cs.wpisuitetng.modules.postboard.model.PostBoardModel;
import edu.wpi.cs.wpisuitetng.modules.postboard.view.BoardPanel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;

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
    public void testActionPerformed_EmptyMessage() throws Exception {
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
