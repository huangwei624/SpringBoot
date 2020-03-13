package life.lovestudy.service;


import life.lovestudy.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {
	
	ServerResponse createToken();
	
	void checkToken(HttpServletRequest request);
	
}
