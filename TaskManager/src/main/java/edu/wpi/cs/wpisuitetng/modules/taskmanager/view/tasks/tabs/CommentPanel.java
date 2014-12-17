/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.tabs;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.attributes.Comment;

/**
 * Panel holding interaction with comments
 * @author R2-team2
 * @version $Revision: 1.0 $
 *
 */
@SuppressWarnings("serial")
public class CommentPanel extends JPanel {
    
    /**
     * Constructor for CommentPanel
     * @param comment
     */
    public CommentPanel(Comment comment) {
        setBorder(BorderFactory.createLineBorder(Color.black)); // Set note border

        // Create a text area containing the note's message
        final JTextArea message = new JTextArea(comment.getMessage());
        message.setLineWrap(true);
        message.setWrapStyleWord(true);
        message.setEditable(false); // Do not allow to be edited

        // Give the message a black border with 2px padding inside
        final Border b =
                BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black),
                        BorderFactory.createEmptyBorder(2, 2, 2, 2));
        message.setBorder(b);

        // Get user name of user who created note
        final String user = comment.getUser();

        // Get the date the note was added, then format it to display correctly
        final Date date = new Date(comment.getTimestamp());
        final Format format = new SimpleDateFormat("MMMMM d, yyyy 'at' hh:mm:ss aaa");
        final String commentDate = format.format(date).toString();
        final JLabel commentInfo = new JLabel("by " + user + " on " + commentDate);

        // Create a layout manager for this note panel
        setLayout(new GridBagLayout());
        final GridBagConstraints commentConstraints = new GridBagConstraints();

        commentConstraints.fill = GridBagConstraints.HORIZONTAL; // Fill elements horizontally
        commentConstraints.gridy = 0; // Row 0
        commentConstraints.weightx = 1; // Fill the width
        commentConstraints.insets = new Insets(2, 2, 2, 2); // 2px margin
        this.add(message, commentConstraints); // Add message to notePanel

        commentConstraints.anchor = GridBagConstraints.SOUTHEAST; // Display info in btm-right
        commentConstraints.fill = GridBagConstraints.NONE; // Don't fill elements
        commentConstraints.gridy = 1; // Row 1
        this.add(commentInfo, commentConstraints); // Add info to notePanel
    }

}
