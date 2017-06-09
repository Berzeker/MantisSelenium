package com.cgi.mantisgama.credentials;

public class Credentials {
	
	private String url;
	final private String login;
	final private String password;
	
	
	public Credentials(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
