package life.lovestudy.controller;

import life.lovestudy.common.ServerResponse;
import life.lovestudy.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {
	
	@Autowired
	private TokenService tokenService;
	
	@GetMapping
	public ServerResponse token() {
		return tokenService.createToken();
	}
	
}
