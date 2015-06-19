package alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayUserUserinfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserUserinfoShareResponse;

public class TestUserinfo {

	//支付宝网关地址
	String serverUrl = "https://openapi.alipay.com/gateway.do";
	//应用ID
	String appId = "2014123100022800";
	//返回结果格式：xml、json;
	String format = "json";
	//默认值
	String prodCode ="WAP_FAST_LOGIN";
	//商户RSA私钥
	String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALLj8YM7fiqTgFukWAwUzMP6RRGjAcqL2HWez452tsgpfO4cseZKCNw33vsXg0yDABKYw12zO2ymQXMFcPic0RW3JbwHptbTnVSGZjaYJ6U2Tq/TwBGIVCtjktNEaCwa/gKlovaHqwh/ooCwcaADUzHZ8y1rfn71XmJHBsATLdilAgMBAAECgYBpL3yHxIiH8lkMCZM371cDePn5x9Y6yYXKi0nRo94Pzc1GmRrFpQyWqEd1brrmVzTIuohNZSDw3sUnI7DsjhCl7gpuuKDxlGhCfjdcgM478/LTkd/GncI024STY8NH4QLlAsIbUCbs1L+S3PyBhogslPJLKRhLox9slBBDOahTgQJBAO3ohcp1IYgKr9Wkl0Ok0cRP8+pyHkGsq7WGySNgfe2V4noFWbGzgmH6voH/+EOpK2RRIkHqc4cbBldD0sqmbAUCQQDAfn2Z4JJxKxukdeWrG7yVnjJWRb4XvYs26N7YeVRzbA7wn05jzoBl6yaeMEuDQHtw1lvlYAJmUTt7Ep494fwhAkBcFt1y+NP57OkTMZq7vFjWPHyWC4ZatZPhuEKzRu53wMThpqCJgIq/kmebupFG33zEYAN6sF33tiZGMSKu/qxhAkEAsauOrprTT9UkHHYdWAOv2sE7YNnajPsMJdBm75N9WVOhroK7ze9qH6gd6uBUsn6v8QRJqbsmO946cmxK1n9hQQJBAJnnDqxoBEiIZkopWC/Hv8xz3B3cbKuh0K56/ofm6TwgJ0mVzOAaVn7FaWjLXQDu+cjsjqOb927JbGW9Wu9XO/k=";
	//字符集格式
	String charset = "utf-8";
	
	String access_token = "kuaijieBc2e511d0121242f38317b03ed77b4C06";
	public static void main (String[] args) throws AlipayApiException{
		TestUserinfo testUserinfo = new TestUserinfo();
		
		//请求对象
		AlipayUserUserinfoShareRequest req = new AlipayUserUserinfoShareRequest ();
		req.setProdCode(testUserinfo.prodCode);
		AlipayClient client = new DefaultAlipayClient(testUserinfo.serverUrl, testUserinfo.appId, testUserinfo.privateKey,testUserinfo.format, testUserinfo.charset);
		//返回结果对象
		AlipayUserUserinfoShareResponse res = client.execute(req,testUserinfo.access_token);
		
		System.out.println("code:"+res.getErrorCode());
		System.out.println("msg:"+res.getMsg());

		System.out.println("CertNo:"+res.getCertNo()+",email:"+res.getEmail()+",mobile:"+res.getMobile()+",realName:"+res.getRealName()+",userId:"+res.getUserId());
	}
	
}
