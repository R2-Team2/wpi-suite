/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors:
 * R2-Team2, Structure from NotesList in the Requirements Manager
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.models.attributes;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;

/**
 * A thread of comments on a task.
 *
 * @version $Revision: 1.0 $
 * @author R2-team2
 */
public class CommentList {

    private final List<Comment> comments;

    /**
     * Stores a thread of comments.
     */

    public CommentList() {
        comments = new LinkedList<Comment>();
    }

    /**
     * Use this function to get a list iterator that you can use to cycle through the elements of
     * the list
     * 
     * @param index The index of the list that you want the iterator to start on
     * 
     * @return The iterator containing all the elements of the list
     */
    public ListIterator<Comment> getIterator(int index) {
        return comments.listIterator(index);
    }

    /**
     * Getter for the linked list of comments.
     *
     * @return the linked list of comments.
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Adds a comment to the thread.
     *
     * @param msg The message of the comment to be added.
     * @return The comment that was just added to the notes.
     */
    public Comment add(String msg) {
        final int id = comments.size() + 1;
        final long time = System.currentTimeMillis();
        final String user = ConfigManager.getConfig().getUserName();
        final Comment newComment = new Comment(id, user, time, msg);
        comments.add(newComment);
        return newComment;
    }
}
