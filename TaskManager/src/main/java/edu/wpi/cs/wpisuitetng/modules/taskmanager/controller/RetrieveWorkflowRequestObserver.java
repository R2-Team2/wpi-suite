/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

/**
 * Observer for processing response for workflow GET request.
 *
 * @version $Revision: 1.0 $
 * @author R2-Team2
 */
public class RetrieveWorkflowRequestObserver implements RequestObserver {

    /** The controller. */
    private final RetrieveWorkflowController controller;

    /**
     * This method is called when information about an RetrieveWorkflow which was previously
     * requested using an asynchronous interface becomes available.
     *
     * @param controller the controller
     */
    public RetrieveWorkflowRequestObserver(RetrieveWorkflowController controller) {
        this.controller = controller;
    }

    @Override
    public void responseSuccess(IRequest iReq) {
    	int counter = 0;
        if (counter==0)
        {
        	System.out.println("Request to retrieve WorkFlow was successful.");
        	final ResponseModel response = iReq.getResponse();
        	final String responseBody = response.getBody();
        	//System.out.println("Response " + responseBody);
        	controller.displayWorkflow(WorkFlow.fromJsonArray(responseBody));
        	counter +=1;
        }

    }
    
    

    @Override
    public void responseError(IRequest iReq) {
        System.err.println("Request to retrieve WorkFlow resulted in an error.");

    }

    @Override
    public void fail(IRequest iReq, Exception exception) {
        // TODO Auto-generated method stub

    }

}
