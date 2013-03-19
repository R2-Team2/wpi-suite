package edu.wpi.cs.wpisuitetng.modules.RequirementManager.controller;
//package edu.wpi.cs.wpisuitetng.modules.RequirementManager.controller;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
///**
// * This controller coordinates retrieving all of the messages
// * from the server. This controller is called when the user
// * clicks the refresh button.
// * 
// * 
// * 
// */
//
//public class GetMessagesController implements ActionListener {
//
//	private final RequirementManagerModel model;
//	
//	public GetMessagesController(RequirementManagerModel model) {
//		this.model = model;
//	}
//	
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// Send a request to the core to save this message
//		final Request request = Network.getInstance().makeRequest("RequirementManager/RequirementManagerMessage", HttpMethod.GET); // GET == read
//		request.addObserver(new GetMessagesRequestObserver(this)); // add an observer to process the response
//		request.send(); // send the request
//	}
//
//	/**
//	 * Add the given messages to the local model (they were received from the core).
//	 * This method is called by the GetMessagesRequestObserver
//	 * 
//	 * @param messages an array of messages received from the server
//	 */
//	public void receivedMessages(RequirementManagerMessage[] messages) {
//		// Empty the local model to eliminate duplications
//		model.emptyModel();
//
//		// Make sure the response was not null
//		if (messages != null) {
//
//			// add the messages to the local model
//			model.addMessages(messages);
//		}
//	}
//}
