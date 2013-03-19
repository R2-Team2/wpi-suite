//package edu.wpi.cs.wpisuitetng.modules.RequirementManager.controller;
//
//import edu.wpi.cs.wpisuitetng.network.RequestObserver;
//import edu.wpi.cs.wpisuitetng.network.models.IRequest;
//
///**
// * This observer handles responses to requests for all
// * Requirement Manager messages.
// * 
// * @author 
// *
// */
//
//public class GetMessagesRequestObserver implements RequestObserver {
//
//	public GetMessagesController controller;
//	
//	public GetMessagesRequestObserver(GetMessagesController controller) {
//		this.controller = controller;
//	}
//	
//	/*
//	 * Parse the messages out of the response body and pass them to the controller
//	 * 
//	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
//	 */
//	@Override
//	public void responseSuccess(IRequest iReq) {
//		RequirementManagerMessage[] messages = RequirementManagerMessage.fromJsonArray(iReq.getResponse().getBody());
//		controller.receivedMessages(messages);
//
//	}
//
//	/*
//	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
//	 */
//	@Override
//	public void responseError(IRequest iReq) {
//		fail(iReq, null);
//	}
//
//	/*
//	 * Put an error message in the RequirementManagerPanel if the request fails.
//	 * 
//	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng.network.models.IRequest, java.lang.Exception)
//	 */
//	@Override
//	public void fail(IRequest iReq, Exception exception) {
//		RequirementManagerMessage[] errorMessage = {new RequirementManagerMessage("Error retrieving messages.")};
//		controller.receivedMessages(errorMessage);
//	}
//
//}
