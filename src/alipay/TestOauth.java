package alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
public class TestOauth {

	//支付宝网关地址
	String serverUrl = "https://openapi.alipay.com/gateway.do";
//	String serverUrl = "http://openapi.alipaydev.com/gateway.do";
	//应用ID
	String appId = "2015050600061785";
	//返回结果格式：xml、json;
	String format = "json";
	//商户RSA私钥
	String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC0ALyF03W37rNrsPeWl17giuOjzJn0hKzXZCJgc8SVDRp8LwIb8j46ta0kHs3E05fKr72BECZSFdIzeXO1EdhjERs5BfZFwFa7dFsWWe9Cqvm6tFil8zi42zmDieT0pw6AOcr5c/m69F4HhSjZ/IAC111TFbmp7/sG4OwodcCUMZJVfuel+3aaLwTAv+wnG7qFwpzbEN/QS01E7YsJULI50lNpIrporMyug8bpc3SgXmmBjvsvCZXm2KrVPC/+67tolg8jOVhAW8259txGHXrdGaLkLPX0+V6EYEapfS+rgqqMQCu1L7IAe9AdWqUmOP1f3YGwFy7vl8e4MFPZ1vITAgMBAAECggEARzajXy6xu/sr8McqrOFmH9/S/9LKdfqnhu+OyioC9BUjDU5rvzKve/q0bT3imqQ7wWyVxKHnB0KqzsKWDGTq/wadhondTOszZSY7gzRBfFa4Ri1jhQQcxMW2k1vSwRf2nLRAoUBI5LYZHVCJQPSskHMz8NL/iUPwRGBoDfnIHthzix7culjEgnjiKqN3klZpY3GSgmmCfUXZ6IlQjDRfnGpfoVY48M/furRpowgqUGHku0v3bZWlE72VkgznQB6+UnQlr73OlElG4ymdcMDj+T6iA9Ftck54Uek9+rhMx5vqhR8z1DsCitPVmYby3QG+iLPr5hcRkNzsKc/R0Wl/wQKBgQDmg9ZboPCWoqk0E5A+POY3xUhX7ncs9pJTFUdMX0RHq71kLTbJMyrKfG8GfHaZc7wvLuNyXAPjR6X/ej0SRLrGI79zNtCR5a9KbcMXrR+39abhVqfMK+IM1uDu0jLHNIUefWOuaY171LDxVQe1FVu1qkS7gl65OnunHnusgmEa9QKBgQDH50b/kTEdyFBLY5fDgBGxQejfYqXQe6UAXPTn9b/W1vfTtanOivb8AlTY1qfkLG8mrsNcp5uXMwmA8UlF4fEmtBukpxoyyLSeB99nIagrszYj3l6vAFgQMWRUkDfuCMubDjZeTFsicgde7VMNaQxmpgML57U5hPEv78NjepPD5wKBgAOfctEB2IYljE87N3si7eNWgGqRXuIgBfC0VuiGXsZZlqJqa4ACA9wm1H/fmOWUghKjxANaGt2JlLxNKYSN+/l9OlcPN3vzhiu/Sd+nYuY7QCIugHie//B/uA1I1G95DdaK1fQ5yHV3xEgms/ZOnaBfSrE/vUlf9+2Kdxsg+XG5AoGAGe4ZGouF2HwkVjMb+4MASILpJxjPs2Rmec6VAgWxlbeau/6DgUQrV3Mh+GKXspRE8rqWos0m9IztPamOvJ7bPg78yvXWAyJfs2QICYOnIcwESNDObVrZEXimsAVpC9peNsW4LbEv3R73b8vPCx1GO4ZrPL8YldZ1xqcOLDg5NJ8CgYEAzjnw8YW2bTPXFqdPNPUwhzKZiht336JS7DPIv/rr+fWn57/gxNTZJ/MZ+WLjeXTjBGnOo9D+81tZ20JaX4dIo+2oTgRpId9t4fTT3OWvDNycezxqgnIfeU3dCXK56SMAgKwp90d1wgU0zjrKWj5uEbW+NSU0ojXnvEP8x8se1Vw=";//字符集格式
	String charset = "utf-8";
	//客户端返回的auth_code
	String auth_code = "6b0873b543ed4a99a3de77a00b2c0X15";
	public static void main (String[] args) throws AlipayApiException{
		TestOauth testOauth = new TestOauth();
		//请求对象
		AlipaySystemOauthTokenRequest req = new AlipaySystemOauthTokenRequest();
		req.setCode(testOauth.auth_code);
		//GrantType传固定值authorization_code
		req.setGrantType("authorization_code");
		AlipayClient client = new DefaultAlipayClient(testOauth.serverUrl, testOauth.appId, testOauth.privateKey, testOauth.format, testOauth.charset);
		
		//返回结果对象
		AlipaySystemOauthTokenResponse res = client.execute(req);
		System.out.println("code:"+res.getErrorCode());
		System.out.println("msg:"+res.getMsg());

		System.out.println("alipayUserId:"+res.getAlipayUserId()+",accessToken:"+res.getAccessToken()+",reExpiresIn:"+res.getReExpiresIn());
	}
	
}
