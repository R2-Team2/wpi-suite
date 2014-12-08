/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

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
        // TODO Auto-generated method stub

    }

    @Override
    public void responseError(IRequest iReq) {
        // TODO Auto-generated method stub

    }

    @Override
    public void fail(IRequest iReq, Exception exception) {
        // TODO Auto-generated method stub

    }

}
