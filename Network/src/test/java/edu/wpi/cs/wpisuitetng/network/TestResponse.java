package edu.wpi.cs.wpisuitetng.network;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class TestResponse {

	@Test
	public void testConstructor(){
		int responseCode = 200;
		String responseMessage = "Test Response";
		Map<String, List<String>> headers = new HashMap<String, List<String>>();
		String responseBody = "Test Response Body";
	
		Response response = new Response(responseCode, responseMessage, headers, responseBody);
		
		assertNotNull(response);
		assertEquals(response.getBody(), responseBody);
		assertEquals(response.getStatusCode(), responseCode);
		assertEquals(response.getHeaders(), headers);
		assertEquals(response.getStatusMessage(), responseMessage);
	}
	
}
