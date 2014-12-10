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

public class CommentPanel extends JPanel {

    public CommentPanel(Comment comment) {
        setBorder(BorderFactory.createLineBorder(Color.black)); // Set note border

        // Create a text area containing the note's message
        JTextArea message = new JTextArea(comment.getMessage());
        message.setLineWrap(true);
        message.setWrapStyleWord(true);
        message.setEditable(false); // Do not allow to be edited

        // Give the message a black border with 2px padding inside
        Border b =
                BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black),
                        BorderFactory.createEmptyBorder(2, 2, 2, 2));
        message.setBorder(b);

        // Get user name of user who created note
        String user = comment.getUser();

        // Get the date the note was added, then format it to display correctly
        Date date = new Date(comment.getTimestamp());
        Format format = new SimpleDateFormat("MMMMM d, yyyy 'at' hh:mm:ss aaa");
        String commentDate = format.format(date).toString();
        JLabel commentInfo = new JLabel("by " + user + " on " + commentDate);

        // Create a layout manager for this note panel
        setLayout(new GridBagLayout());
        GridBagConstraints commentConstraints = new GridBagConstraints();

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
