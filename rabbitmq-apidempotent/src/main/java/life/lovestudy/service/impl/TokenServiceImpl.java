package life.lovestudy.service.impl;

import life.lovestudy.common.Constant;
import life.lovestudy.common.ResponseCode;
import life.lovestudy.common.ServerResponse;
import life.lovestudy.exception.ServiceException;
import life.lovestudy.service.TokenService;
import life.lovestudy.utils.JedisUtil;
import life.lovestudy.utils.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class TokenServiceImpl implements TokenService {
	
	private static final String TOKEN_NAME = "token";
	
	@Autowired
	private JedisUtil jedisUtil;
	
	@Override
	public ServerResponse createToken() {
		String str = RandomUtil.UUID32();
		StrBuilder token = new StrBuilder();
		token.append(Constant.Redis.TOKEN_PREFIX).append(str); // token:xxxx
		
		jedisUtil.set(token.toString(), token.toString(), Constant.Redis.EXPIRE_TIME_HOUR);
		
		return ServerResponse.success(token.toString());
	}
	
	@Override
	public void checkToken(HttpServletRequest request) {
		String token = request.getHeader(TOKEN_NAME);
		if (StringUtils.isBlank(token)) {   // header中不存在token
			token = request.getParameter(TOKEN_NAME);
			if (StringUtils.isBlank(token)) {   // parameter中也不存在token
				throw new ServiceException(ResponseCode.ILLEGAL_ARGUMENT.getMsg());
			}
		}
		
		if (!jedisUtil.exists(token)) {
			throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());
		}
		
		Long del = jedisUtil.del(token);
		if (del <= 0) {
			throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());
		}
	}
	
}
