///**
// * 
// */
//package edu.wpi.cs.wpisuitetng.modules.RequirementManager.controller;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import edu.wpi.cs.wpisuitetng.network.Network;
//import edu.wpi.cs.wpisuitetng.network.Request;
//
///**
// * This controller responds when the user clicks the Submit button by
// * adding the contents of the message text field to the model as a new
// * message.
// * 
// * @author DevComm
// *
// */
//public class AddMessageController implements ActionListener {
//
//	private final RequirementManagerModel model; // subject to change
//	private final BoardPanel view; // subject to change
//	
//	/**
//	 * Construct an AddMessageController for the given model, view pair
//	 * @param model the model containing the messages
//	 * @param view the view where the user enters new messages
//	 */
//	public AddMessageController(RequirementManagerModel model, BoardPanel view) {
//		this.model = model;
//		this.view = view;
//	}
//	
//	/* 
//	 * This method is called when the user clicks the Submit button
//	 * 
//	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
//	 */
//	@Override
//	public void actionPerformed(ActionEvent event) {
//		// Get the text that was entered
//		String message = view.getTxtNewMessage().getText();
//		
//		// Make sure there is text
//		if (message.length() > 0) {
//						
//			// Clear the text field
//			view.getTxtNewMessage().setText("");
//			
//			// Send a request to the core to save this message
//			// variables need to be revised
//			final Request request = Network.getInstance().makeRequest("RequirementManager/RequirementManagerMessage", HttpMethod.PUT); // PUT == create
//			request.setBody(new RequirementManagerMessage(message).toJSON()); // put the new message in the body of the request
//			request.addObserver(new AddMessageRequestObserver(this)); // add an observer to process the response
//			request.send(); // send the request
//		}
//	}
//	
//	/**
//	 * When the new message is received back from the server, add it to the local model.
//	 * @param message
//	 */
//	public void addMessageToModel(RequirementManagerMessage message) {
//		model.addMessage(message);
//	}
//
//}
