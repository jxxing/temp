package alipay;

import com.alipay.api.request.AlipayUserGetRequest;
import com.alipay.api.response.AlipayUserGetResponse;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
public class TestGetInfo {
	public static void main (String[] args){
		TaobaoClient client=new DefaultTaobaoClient(url, appkey, secret);
		AlipayUserGetRequest req=new AlipayUserGetRequest();
		req.setAuthToken("6967910612234c9aa2e5e631be80b4bb");
		req.setFields("alipay_user_id,user_status,user_type,certified,real_name,logon_id,sex");
		AlipayUserGetResponse response = client.execute(req , sessionKey);
	}
}
