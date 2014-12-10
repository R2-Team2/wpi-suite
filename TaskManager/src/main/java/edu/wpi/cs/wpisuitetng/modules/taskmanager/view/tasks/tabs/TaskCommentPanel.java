/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.tabs;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;

/**
 * @author justinhess
 * @version $Revision: 1.0 $
 */
public class TaskCommentPanel extends JPanel {
    static final long serialVersionUID = -3114980850099223953L;

    private int commentsAdded;
    private final Task currentTask;
    private JTextArea commentMsg = new JTextArea();
    private final JScrollPane commentScroll;
    private final JButton buttonAddComment;
    private final JButton buttonClear;
    private final JLabel errorMsg;

    /**
     * Constructor for the requirement note panel
     *
     * @param current current requirement
     */
    public TaskCommentPanel(Task currentTask) {
        this.currentTask = currentTask;
        commentsAdded = 0;

        Component commentField = buildCommentField();
        commentScroll = new JScrollPane();

        // Buttons to be added to the bottom of the NotePanel
        buttonAddComment = new JButton("Add Comment");
        buttonClear = new JButton("Clear");
        buttonAddComment.setEnabled(false);
        buttonClear.setEnabled(false);

        // Error message label in case no note was included
        errorMsg = new JLabel();

        // Layout manager for entire note panel
        final GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        final GridBagConstraints c = new GridBagConstraints();

        // Layout manager for panel that contains the buttons
        final GridBagLayout bottomLayout = new GridBagLayout();
        JPanel bottomPanel = new JPanel(bottomLayout);
        final GridBagConstraints bc = new GridBagConstraints();

        // Create new scroll pane for notes
        commentScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // Always show scroll bar
        commentScroll.setMinimumSize(new Dimension(100, 100));

        c.fill = GridBagConstraints.BOTH; // Fill grid cell with elements
        c.weightx = 1; // Fill horizontal space
        c.weighty = 1; // Fill all the vertical space
        this.add(commentScroll, c);

        c.gridy = 1; // Row 1
        c.weighty = 0; // Fill 0% of vertical space
        this.add(commentField, c);

        bc.anchor = GridBagConstraints.WEST; // Anchor buttons to west of bottom
        // panel
        bottomPanel.add(buttonAddComment, bc); // Include "Add note" button to
        // bottom panel

        bc.gridx = 1; // Column 1
        bottomPanel.add(buttonClear, bc); // Include "Clear" button to bottom
        // panel

        bc.gridx = 2; // Column 2
        bottomPanel.add(errorMsg, bc); // Add error message label to bottom
        // panel

        c.weighty = 0; // Do not stretch
        c.gridy = 2; // Row 2
        c.fill = GridBagConstraints.NONE; // Do not fill cell
        c.anchor = GridBagConstraints.WEST; // Anchor buttons to west of panel
        this.add(bottomPanel, c); // Add buttons to the panel

        setupListeners();
        refresh();
    }

    /**
     * Refreshes the note panel
     */
    private void refresh()
    {
        // noteScroll.setViewportView(CommentPanel.createList(currentRequirement.getNotes()));
    }

    /**
     * Sets up the listeners
     */
    private void setupListeners()
    {
        // Listener for add note button
        buttonAddComment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display error message if there is no text in noteMessage
                if (commentMsg.getText().length() <= 0) {
                    errorMsg.setText(" Error: Must add text to create note.");
                } else {

                    String msg = commentMsg.getText(); // Get text from
                    // noteMessage

                    // Clear all text areas
                    commentMsg.setText("");
                    errorMsg.setText("");
                    buttonClear.setEnabled(false);
                    buttonAddComment.setEnabled(false);

                    // Add note to requirement
                    currentTask.addComment(msg);

                    refresh();
                    commentsAdded++;
                    // TODO: Update database so task stores new comment
                }
            }
        });

        // Listener for the Clear button
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear all text fields
                commentMsg.setText("");
                errorMsg.setText("");
                buttonClear.setEnabled(false);
                buttonAddComment.setEnabled(false);
            }
        });
    }

    /**
     * Method buildNoteMessage.
     *
     * @return JTextArea
     */
    private Component buildCommentField() {
        commentMsg.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyReleased(KeyEvent e)
            {
                boolean enabledButtons = !commentMsg.getText().trim().isEmpty();
                buttonAddComment.setEnabled(enabledButtons);
                buttonClear.setEnabled(enabledButtons);
            }
        });

        // Create text area for note to be added
        commentMsg.setLineWrap(true); // If right of box is reach, goes down a
        // line
        commentMsg.setWrapStyleWord(true); // Doesn't chop off words
        commentMsg.setMinimumSize(new Dimension(50, 50));
        commentMsg.setSize(new Dimension(100, 100));
        Border b = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(4, 4, 4, 4));
        commentMsg.setBorder(b);

        JScrollPane scroller = new JScrollPane(commentMsg);
        scroller.setMinimumSize(new Dimension(50, 50));
        scroller.setPreferredSize(new Dimension(60, 60));

        return scroller;
    }

    /**
     * @return the currentRequirement
     */
    Task getCurrentTask() {
        return currentTask;
    }

    /**
     * @return the noteScroll
     */
    JScrollPane getNoteScroll() {
        return commentScroll;
    }

    /**
     * @return the buttonAddNote
     */
    JButton getButtonAddNote() {
        return buttonAddComment;
    }

    /**
     * @return the buttonClear
     */
    JButton getButtonClear() {
        return buttonClear;
    }

    /**
     * @param notesAdded the notesAdded to set
     */
    void setNotesAdded(int notesAdded) {
        commentsAdded = notesAdded;
    }

    /**
     * @param noteMessage the noteMessage to set
     */
    void setNoteMessage(JTextArea noteMessage) {
        commentMsg = noteMessage;
    }

    /**
     * @return the note message text area
     */
    public JTextArea getNoteMessage() {
        return commentMsg;
    }

    /**
     * @return the button for adding a note
     */
    public JButton getAddNoteButton() {
        return buttonAddComment;
    }

    /**
     * @return the number of notes added
     */
    public int getCommentsAdded() {
        return commentsAdded;
    }

    /**
     * Method getClearButton.
     *
     * @return JButton
     */
    public JButton getClearButton() {
        return buttonClear;
    }

    /**
     * Method getErrorMsg.
     *
     * @return JLabel
     */
    public JLabel getErrorMsg() {
        return errorMsg;
    }
}
