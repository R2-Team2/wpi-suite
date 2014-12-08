/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.WorkFlowView;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * Controller for sending a workflow GET request.
 *
 * @version $Revision: 1.0 $
 * @author R2-Team2
 */
public class RetrieveWorkflowController {

    private final WorkFlowView view;

    /**
     * Constructor for Controller
     */
    public RetrieveWorkflowController(WorkFlowView view) {
        this.view = view;
    }

    public void requestWorkflow() {
        Request request = Network.getInstance().makeRequest("taskmanager/workflow", HttpMethod.GET);
        request.addObserver(new RetrieveWorkflowRequestObserver(this));
        request.send();
        
    }

    public void displayWorkflow(WorkFlow[] workFlowObj) {
        view.setWorkFlowObj(workFlowObj[0]);
        WorkFlowView.getInstance().setWorkFlowObj(workFlowObj[0]);
        System.out.println("WorkFlowObject: " + workFlowObj[0].toJson());
        //return view.getWorkFlowObj();
    }
}
