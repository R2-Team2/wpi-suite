package edu.wpi.cs.wpisuitetng.modules.RequirementManager.controller;
//package edu.wpi.cs.wpisuitetng.modules.RequirementManager.controller;
//
//import edu.wpi.cs.wpisuitetng.network.RequestObserver;
//import edu.wpi.cs.wpisuitetng.network.models.IRequest;
//import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;
//
//public class AddMessageRequestObserver implements RequestObserver {
//	
//	private final AddMessageController controller;
//
//	public AddMessageRequestObserver(AddMessageController controller) {
//		this.controller = controller;
//	}
//	
//	/*
//	 * Parse the message that was received from the server then pass them to
//	 * the controller.
//	 * 
//	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
//	 */
//	@Override
//	public void responseSuccess(IRequest iReq) {
//		// Get the response to the given request
//		final ResponseModel response = iReq.getResponse();
//
//		// Parse the message out of the response body
//		final RequirementManagerMessage message = RequirementManagerMessage.fromJson(response.getBody());
//
//		// Pass the message back to the controller
//		controller.addMessageToModel(message);
//	}
//
//	@Override
//	public void responseError(IRequest iReq) {
//		System.err.println("The request to add a message failed.");
//	}
//
//	@Override
//	public void fail(IRequest iReq, Exception exception) {
//		System.err.println("The request to add a message failed.");
//	}
//
//}
