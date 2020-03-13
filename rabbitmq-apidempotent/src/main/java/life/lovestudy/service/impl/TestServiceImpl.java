package life.lovestudy.service.impl;

import life.lovestudy.common.ServerResponse;
import life.lovestudy.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
	
	@Override
	public ServerResponse testIdempotence() {
		return ServerResponse.success("testIdempotence: success");
	}
	
}

