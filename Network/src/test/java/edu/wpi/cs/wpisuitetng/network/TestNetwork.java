package edu.wpi.cs.wpisuitetng.network;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;

import edu.wpi.cs.wpisuitetng.network.configuration.NetworkConfiguration;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class TestNetwork {
	
	private NetworkConfiguration mockNetworkConfig = mock(NetworkConfiguration.class);
	
	private Network network = new Network();
	
	//TODO Fix once request is fixed
	@Test
	public void testMakeRequest(){
		String ApiUrl = "http://www.test.com";
		String path = "model";
		HttpMethod method = HttpMethod.GET;
		
		when(mockNetworkConfig.getApiUrl()).thenReturn(ApiUrl);
		network.setDefaultNetworkConfiguration(mockNetworkConfig);
		
		Request request = network.makeRequest(path, method);
		
		assertNotNull(request);
		assertEquals(ApiUrl + "/" + path, request.getUrl().toString());
		assertEquals(method, request.getHttpMethod());
	}
	
	@Test(expected=NullPointerException.class)
	public void testMakeRequest_null(){
		network.makeRequest("www.test.com", null);
	}
	
	@Test
	public void testGetInstance(){
		Network result = Network.getInstance();
		
		assertNotNull(result);
	}
	
	@Test
	public void testGetInstance_sameInstance(){
		Network firstInstance = Network.getInstance();
		Network secondInstance = Network.getInstance();
		
		assertNotNull(firstInstance);
		assertNotNull(secondInstance);
		assertEquals(firstInstance, secondInstance);
	}

	@Test
	public void testInitNetwork(){
		Network.initNetwork(network);
		
		assertEquals(Network.getInstance(), network);
	}
	
	@Test(expected=NullPointerException.class)
	public void testSetDefaultNetworkConfiguration_nullConfig(){
		network.setDefaultNetworkConfiguration(null);
	}
	
	@Test
	public void testGetDefaultNetworkConfiguration(){
		when(mockNetworkConfig.getObservers()).thenReturn(new ArrayList<RequestObserver>());
		
		network.setDefaultNetworkConfiguration(mockNetworkConfig);
		NetworkConfiguration networkConfiguration = network.getDefaultNetworkConfiguration();
		
		assertEquals(mockNetworkConfig.getApiUrl(), networkConfiguration.getApiUrl());
		assertEquals(mockNetworkConfig.getObservers(), networkConfiguration.getObservers());
		assertEquals(mockNetworkConfig.getRequestHeaders(), networkConfiguration.getRequestHeaders());
	}
	
	@Test(expected=RuntimeException.class)
	public void testGetDefaultNetworkConfiguration_nullConfiguration(){
		network.getDefaultNetworkConfiguration();
	}
}
