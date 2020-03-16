package life.lovestudy.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class AuthenticateTokenUtil {
	
	private static final long EXPIRE_TIME = 1000*60*60*24;  // token过期时间2分钟
	private static final String TOKEN_SECRET = "huangwei";  // 秘钥盐
	
	// token 生成
	public static String getToken(String username, String password){
		Date endTime = new Date(System.currentTimeMillis()  + EXPIRE_TIME);
		return JWT.create()
				        .withIssuer("auth0")
				        .withClaim("username", username)
						.withClaim("password", password)
				        .withExpiresAt(endTime)
				        // 使用了HMAC256签名算法。
				        .sign(Algorithm.HMAC256(TOKEN_SECRET));
	}
	
	// token 验证
	public static boolean verifyToken(String token){
		try {
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
			DecodedJWT jwt = verifier.verify(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
