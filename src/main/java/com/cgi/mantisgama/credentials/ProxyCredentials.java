package com.cgi.mantisgama.credentials;

public class ProxyCredentials {

	final private String host;
	final private String port;
	final Credentials credentials;
	
	
	public ProxyCredentials(String host, String port, String login, String password) {
		this.host = host;
		this.port = port;
		credentials = new Credentials(login, password);
	}
	
	public ProxyCredentials(String host, String port) {
		this.host = host;
		this.port = port;
		this.credentials = null;
	}
	
	
	public String getHost() {
		return host;
	}
	
	public String getPort() {
		return port;
	}

	public String getLogin() {
		return credentials.getLogin();
	}

	public String getPassword() {
		return credentials.getPassword();
	}
	
}
