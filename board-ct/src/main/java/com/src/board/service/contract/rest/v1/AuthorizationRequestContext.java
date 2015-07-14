package com.src.board.service.contract.rest.v1;

public class AuthorizationRequestContext {

	/**
     * The relative url of the request which starts at the root of the requested resource
     */
    private final String requestUrl;

    /**
     * The Http method (POST, GET, DELETE, PUT)
     */
    private final String httpMethod;
    
    /**
     * The AuthorizationToken which should be in a format that the appropriate AuthorizationService can understand
     */
    private final String authorizationToken;

	public AuthorizationRequestContext(String requestUrl, String httpMethod,
			String authorizationToken) {
		super();
		this.requestUrl = requestUrl;
		this.httpMethod = httpMethod;
		this.authorizationToken = authorizationToken;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public String getAuthorizationToken() {
		return authorizationToken;
	}
    
    
}
