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
	String appId = "2015051600077178";
	//返回结果格式：xml、json;
	String format = "json";
	//商户RSA私钥
	String privateKey = "B99nIagrszYj3l6vAFgQMWRUkDfuCMubDjZeTFsicgde7VMNaQxmpgML57U5hPEv78NjepPD5wKBgAOfctEB2IYljE87N3si7eNWgGqRXuIgBfC0VuiGXsZZlqJqa4ACA9wm1H/fmOWUghKjxANaGt2JlLxNKYSN+/l9OlcPN3vzhiu/Sd+nYuY7QCIugHie//B/uA1I1G95DdaK1fQ5yHV3xEgms/ZOnaBfSrE/vUlf9+2Kdxsg+XG5AoGAGe4ZGouF2HwkVjMb+4MASILpJxjPs2Rmec6VAgWxlbeau/6DgUQrV3Mh+GKXspRE8rqWos0m9IztPamOvJ7bPg78yvXWAyJfs2QICYOnIcwESNDObVrZEXimsAVpC9peNsW4LbEv3R73b8vPCx1GO4ZrPL8YldZ1xqcOLDg5NJ8CgYEAzjnw8YW2bTPXFqdPNPUwhzKZiht336JS7DPIv/rr+fWn57/gxNTZJ/MZ+WLjeXTjBGnOo9D+81tZ20JaX4dIo+2oTgRpId9t4fTT3OWvDNycezxqgnIfeU3dCXK56SMAgKwp90d1wgU0zjrKWj5uEbW+NSU0ojXnvEP8x8se1Vw=";//字符集格式
	String charset = "utf-8";
	//客户端返回的auth_code
	String auth_code = "12316b3e13e74ee2ad0ca60f66098C06";
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
