package life.lovestudy.utils;

import java.util.Random;

public class VerifyCodeUtil {
	private static final String RANDOMSTRING= "23456789abcdefghijkmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ";//随机产生的字符串
	private static final int DEFAULT_SRING_NUMBER = 4;  // 默认随机字符串的数量
	private static final int MAX_SRING_NUMBER = 6;  // 最大限制字符串的数量
	
	public static String getRandomString(){
		return getRandomString(DEFAULT_SRING_NUMBER);
	}
	
	public static String getRandomString(int stringNumber) {
		// 如果小于4或者大于6， 就获取四位验证码
		if(stringNumber<DEFAULT_SRING_NUMBER || stringNumber>MAX_SRING_NUMBER) return getRandomString();
		// 否则获取指定数目的验证码
		Random random = new Random();
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < stringNumber; i++) {
			int index = random.nextInt(RANDOMSTRING.length());
			stringBuffer.append(RANDOMSTRING.charAt(index));
		}
		return stringBuffer.toString();
	}
}
