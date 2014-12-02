/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2, Structure from Notes in the Requirements Manager
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.models.attributes;

/**
 * A comment on a task. Requires an id, a username, timestamp, and a string to define the comment.
 *
 * @version $Revision: 1.0 $
 * @author R2-team2
 */
public class Comment {

    private final int id;
    private final String user;
    private final long timestamp;
    private final String message;

    /**
     * Complete definition of a comment.
     *
     * @param user Name of user who created the note.
     * @param timestamp Time at which note was created.
     * @param message Message to be stored within the note.
     * @param id Task unique ID number of the comment.
     */
    public Comment(int id, String user, long timestamp, String message) {
        this.id = id;
        this.user = user;
        this.timestamp = timestamp;
        this.message = message;
    }

    /**
     * Getter for the ID
     *
     * @return The ID
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the user
     *
     * @return The user
     */
    public String getUser() {
        return user;
    }

    /**
     * Getter for the timestamp
     *
     * @return The timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Getter for the message
     *
     * @return The message
     */
    public String getMessage() {
        return message;
    }

    /*
     * Equals comparator for the comment. Element-wise comparison.
     * @return boolean whether comparison's elements are identical to this object's elements
     */
    @Override
    public boolean equals(Object comparison) {
        boolean same = false;
        if (comparison instanceof Comment) {
            Comment compare = (Comment) comparison;
            if ((compare.getId() == id) && (compare.getMessage().equals(message))
                    && (compare.getTimestamp() == timestamp) && (compare.getUser().equals(user))) {
                same = true;
            }
        }
        return same;
    }

}
