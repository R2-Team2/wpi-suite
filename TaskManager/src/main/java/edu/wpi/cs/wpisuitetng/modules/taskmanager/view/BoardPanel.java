/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * Chris Casola
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddMessageController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.PostBoardModel;

/**
 * This class is a JPanel. It contains the actual post board, a text field
 * for entering a new message, and a submit button for submitting
 * a new message.
 * 
 * @author Chris Casola
 */
public class BoardPanel extends JPanel {
    
    /**
     * 
     */
    private static final long serialVersionUID = 6568533712473785570L;
    
    /** A list box to display all the message on the board */
    private final JList<PostBoardModel> lstBoard;
    
    /** A text field where the user can enter a new message */
    private JTextField txtNewMessage;
    
    /** A button for submitting new messages */
    private final JButton btnSubmit;
    
    private final JScrollPane lstScrollPane;
    
    private final MouseAdapter mouseListener;
    
    /**
     * This is a model for the lstBoard component. Basically it
     * contains the data to be displayed in the list box.
     */
    private final PostBoardModel lstBoardModel;
    
    /**
     * Construct the panel, the three components, and add the
     * three components to the panel.
     * 
     * @param boardModel
     */
    public BoardPanel(PostBoardModel boardModel) {
        
        // Construct the list box model
        lstBoardModel = boardModel;
        
        // Construct the components to be displayed
        lstBoard = new JList<PostBoardModel>(lstBoardModel);
        txtNewMessage = new JTextField("Enter a message here.");
        btnSubmit = new JButton("Submit");
        
        mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                txtNewMessage.setText("");
            }
        };
        
        // Change the font of the JList
        lstBoard.setFont(lstBoard.getFont().deriveFont(11));
        
        // Construct the add message controller and add it to the submit button
        btnSubmit.addActionListener(new AddMessageController(lstBoardModel, this));
        
        // Set the layout manager of this panel that controls the positions of the components
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); // components will  be arranged vertically
        
        // Put the listbox in a scroll pane
        this.lstScrollPane = new JScrollPane(lstBoard);
        lstScrollPane.setPreferredSize(new Dimension(500, 400));
        
        // Clear the contents of the text field when the user clicks on it
        txtNewMessage.addMouseListener(mouseListener);
        
        // Adjust sizes and alignments
        btnSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add the components to the panel
//        add(Box.createVerticalStrut(20)); // leave a 20 pixel gap
//        add(lstScrollPane);
//        add(Box.createVerticalStrut(20));
//        add(txtNewMessage);
//        add(Box.createVerticalStrut(20));
//        add(btnSubmit);
    }
    
    /**
     * @return the txtNewMessage JTextField
     */
    public JTextField getTxtNewMessage() {
        return txtNewMessage;
    }
    
    /**
     * @param txtNewMessage the txtNewMessage to set
     */
    void setTxtNewMessage(JTextField txtNewMessage) {
        this.txtNewMessage = txtNewMessage;
    }
    
    /**
     * @return the lstBoard
     */
    JList<PostBoardModel> getLstBoard() {
        return this.lstBoard;
    }
    
    /**
     * @return the btnSubmit
     */
    JButton getBtnSubmit() {
        return this.btnSubmit;
    }
    
    /**
     * @return the lstBoardModel
     */
    PostBoardModel getLstBoardModel() {
        return this.lstBoardModel;
    }
    
    /**
     * @return the serialversionuid
     */
    static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    /**
     * @return the lstScrollPane
     */
    JScrollPane getLstScrollPane() {
        return this.lstScrollPane;
    }
    
    /**
     * @return the mouseListener
     */
    MouseAdapter getMouseListener() {
        return this.mouseListener;
    }
}
