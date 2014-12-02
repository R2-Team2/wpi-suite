/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors:
 * R2-Team2, Structure from NotesList in the Requirements Manager
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.models.attributes;

import java.util.LinkedList;

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;

/**
 * A thread of comments on a task.
 *
 * @version $Revision: 1.0 $
 * @author R2-team2
 */
public class CommentList {

    private LinkedList<Comment> comments;

    /**
     * Stores a thread of comments.
     */
    public CommentList() {
        comments = new LinkedList<Comment>();
    }

    /**
     * Getter for the linked list of comments.
     *
     * @return the linked list of comments.
     */
    public LinkedList<Comment> getComments() {
        return comments;
    }

    /**
     * Adds a comment to the thread.
     *
     * @param msg The message of the comment to be added.
     * @return The comment that was just added to the notes.
     */
    public Comment add(String msg) {
        int id = comments.size() + 1;
        long time = System.currentTimeMillis();
        String user = ConfigManager.getConfig().getUserName();
        Comment newComment = new Comment(id, user, time, msg);
        comments.add(newComment);
        return newComment;
    }
}
