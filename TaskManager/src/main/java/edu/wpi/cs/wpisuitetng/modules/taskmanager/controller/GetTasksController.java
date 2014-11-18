/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * Chris Casola
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.PostBoardMessage;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller coordinates retrieving all of the messages
 * from the server. This controller is called when the user
 * clicks the refresh button.
 * 
 */
public class GetTasksController implements ActionListener {
    
    private Task getTask;
    
    public GetTasksController(Task aTask) {
        this.getTask = aTask;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Send a request to the core to save this message
        final Request request = Network.getInstance().makeRequest("taskmanager/task/" + getTask.getTaskID(), HttpMethod.GET); // GET == read
        request.addObserver(new GetTasksRequestObserver(this)); // add an observer to process the response
        request.send(); // send the request
    }
    
    /**
     * Add the given messages to the local model (they were received from the
     * core).
     * This method is called by the GetTasksRequestObserver
     * 
     * @param messages an array of messages received from the server
     */
    public void receivedMessages(PostBoardMessage[] messages) {
        // Empty the local model to eliminate duplications
        //Model model = new message();
        
        // Make sure the response was not null
        if (messages != null) {
            
            // add the messages to the local model
          //  ((Model) getTask).addMessages(messages);
        }
    }
}
