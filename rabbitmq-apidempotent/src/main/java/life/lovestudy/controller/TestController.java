package life.lovestudy.controller;

import life.lovestudy.annotation.ApiIdempotent;
import life.lovestudy.common.ServerResponse;
import life.lovestudy.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@ApiIdempotent
	@PostMapping("testIdempotence")
	public ServerResponse testIdempotence() {
		return testService.testIdempotence();
	}
	
}
