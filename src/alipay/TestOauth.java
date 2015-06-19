package alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
public class TestOauth {

	//支付宝网关地址
	String serverUrl = "https://openapi.alipay.com/gateway.do";
	//应用ID
	String appId = "2015050600061785";
	//返回结果格式：xml、json;
	String format = "json";
	//商户RSA私钥
	String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMkAfH/t0TFy5nFlFPGmcW0TkjxNz1vck/Za0WvPIjLBHvWCBXZVco0ywJqdAEkw7zFVTGz3iuua+HKQWogcSJZOzfiQjEqYI+UEX01mryCRdb49qVwTvZIPJNuZ6bFdMyhMluPkn+CV+TeeTCg/BY5QlhVzAOLOVXwUIznS8DevAgMBAAECgYBeYXkUROIGmxIzyqfz9BishBUVdd/ZHZDoi6psOf2v4VidJSlVcDfyrxMbRn9c3rph6tCRFgdILCNfAANSiB1YiOXoj0yANgGvriF0RmF21UqakiklasiuDthUApi+LxwIiX10fBLls0R0UgJ27C+/ITF/gLHEHP+rnxNyFQ5ReQJBAPcpW3arMYsKE1a2IXyhKtvaYU2kS83V1YDAM8ehTQGOZ28voZAwPTRMWSKIE3GlHU/pW+4/wIjAyUqMF7oh9JsCQQDQMI9hoUdZs/BpAvAztoRoK5C96Jl6hZ+xfN1wGI0nzmJuhy3ivMYP35oF0BpN0xUpXa4lGb2uPmaDST3C+9h9AkAT1GOcL7v31cUbbd1d7GhN30VzPz09tGw+uiztVUmiWFSAHSgvM0+VxJJaLOSyd3bUlHplsQO3dRAxRPAaZOMFAkEAseGL4OMoT6DLPS5hhg3gvBDdLyZaXLFnbQM/QNP5Z5WTzNR7hEu+/zkSJP1WPECqyBle/hwsUYDHnk0qFDuTEQJBAIRT1lr9J1qnVYNX0S+iKSiQImiI9Ev2t+Kc5Yw0DXNb/LeLZ0s0hU0Nlae7/V0WbssTZ4H9uKrPi7KedKmRz7w=";//字符集格式
	String charset = "utf-8";
	//客户端返回的auth_code
	String auth_code = "35c4bbc7c63d42a49a76a9d7e63deX06";
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
