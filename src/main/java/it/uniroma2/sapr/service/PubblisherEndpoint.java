package it.uniroma2.sapr.service;

import javax.xml.ws.Endpoint;

public class PubblisherEndpoint {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9999/ws/sapr", new SAPRService());
	}
}
