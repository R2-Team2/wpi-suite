package edu.wpi.cs.wpisuitetng.modules.postboard.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import edu.wpi.cs.wpisuitetng.modules.postboard.model.PostBoardMessage;
import edu.wpi.cs.wpisuitetng.modules.postboard.model.PostBoardModel;
import edu.wpi.cs.wpisuitetng.modules.postboard.view.BoardPanel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class TestAddMessageController {
    
    private PostBoardModel mockModel = mock(PostBoardModel.class);
    private BoardPanel mockView = mock(BoardPanel.class);
    
    private Network mockNetwork = mock(Network.class);
    private Request mockRequest = mock(Request.class);
    
    private ActionEvent mockActionEvent = mock(ActionEvent.class);
    private JTextField mockTextField = mock(JTextField.class);
    private PostBoardMessage message = new PostBoardMessage("Test Message");
    
    private AddMessageController controller = new AddMessageController(mockModel, mockView);
    
    @Test
    public void testActionPerformed() throws Exception {
        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<AddMessageRequestObserver> observerCaptor = ArgumentCaptor.forClass(AddMessageRequestObserver.class);
        Network.setInstance(mockNetwork);
        
        when(mockView.getTxtNewMessage()).thenReturn(mockTextField);
        when(mockTextField.getText()).thenReturn("Test Message");
        when(mockNetwork.makeRequest("postboard/postboardmessage", HttpMethod.PUT)).thenReturn(mockRequest);
        //        when(message.toJson()).thenReturn("Test Json");
        
        //        whenNew(PostBoardMessage.class).withArguments("Test Message").thenReturn(mockMessage);
        //        whenNew(AddMessageRequestObserver.class).withArguments(controller).thenReturn(mockObserver);
        
        controller.actionPerformed(mockActionEvent);
        
        verify(mockView, times(2)).getTxtNewMessage();
        verify(mockTextField, times(1)).setText("");
        
        verify(mockRequest, times(1)).setBody(stringCaptor.capture());
        verify(mockRequest, times(1)).addObserver(observerCaptor.capture());
        verify(mockRequest, times(1)).send();
        
        String requestBody = stringCaptor.getValue();
        AddMessageRequestObserver requestObserver = observerCaptor.getValue();
        
        //Because of date, we can't use assertEquals...
        assertTrue(requestBody.contains("{\"message\":\"Test Message\",\"date\":\""));
        assertTrue(requestBody.contains("\",\"permissionMap\":{}}"));
        
        assertEquals(controller, requestObserver.getController());
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
        controller.addMessageToModel(message);
        
        verify(mockModel, times(1)).addMessage(message);
    }
}
