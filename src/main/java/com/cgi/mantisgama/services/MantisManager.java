package com.cgi.mantisgama.services;

import com.cgi.mantisgama.config.MantisConfig;
import com.cgi.mantisgama.config.SeleniumConfig;
import com.cgi.mantisgama.credentials.Credentials;

public interface MantisManager {

	MantisManager getInstance(SeleniumConfig seleniumConfig, MantisConfig mantisConfig);
	MantisManager getInstance(SeleniumConfig seleniumConfig, MantisConfig mantisConfig, Credentials oktaCredentials);
	
	
}
