/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.util.ArrayList;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.WorkFlowView;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

/**
 * The Class RetrieveTaskStatusRequestObserver.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class RetrieveTaskStatusRequestObserver implements RequestObserver {
    /** Controller given */
    private final RetrieveTaskStatusController controller;

    /**
     * Constructor for the RetrieveTaskStatusRequestObserver.
     */
    public RetrieveTaskStatusRequestObserver(RetrieveTaskStatusController controller) {
        this.controller = controller;
    }

    @Override
    public void responseSuccess(IRequest iReq) {
        final ResponseModel response = iReq.getResponse();
        final String responseBody = response.getBody();
        // System.out.println(responseBody);
        TaskStatus[] tsArry = TaskStatus.fromJsonArray(responseBody);
        if (tsArry.length > 0)
            controller.displayTaskStatuses(tsArry);
        else {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            responseSuccess(iReq);
        }
        ArrayList<TaskStatus> tsList = new ArrayList<TaskStatus>();
        for (int i = 0; i < tsArry.length; i++) {
            tsList.add(tsArry[i]);
        }
        WorkFlowView.getInstance().setStatuses(tsList);
    }

    @Override
    public void responseError(IRequest iReq) {
        System.err.println("The request to retrieve TaskStatuses resulted in an error.");

    }

    @Override
    public void fail(IRequest iReq, Exception exception) {
        System.err.println("The request to retrieve TaskStatuses failed.");

    }

}
