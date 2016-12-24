package com.rd.ifaes.core.core.sign.cfca.test;

import cfca.sadk.algorithm.common.PKIException;
import cfca.trustsign.common.vo.cs.HeadVO;
import cfca.trustsign.common.vo.cs.ProxySignVO;
import cfca.trustsign.common.vo.request.tx3.Tx3102ReqVO;

import com.rd.ifaes.core.core.sign.cfca.connector.HttpConnector;
import com.rd.ifaes.core.core.sign.cfca.converter.JsonObjectMapper;
import com.rd.ifaes.core.core.sign.cfca.util.SecurityUtil;

public class Test3102 {
    public static void main(String[] args) throws PKIException {
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.init();

        Tx3102ReqVO tx3102ReqVO = new Tx3102ReqVO();
        HeadVO head = new HeadVO();
        head.setTxTime("20160102235959");

        ProxySignVO proxySignVO = new ProxySignVO();
        proxySignVO.setUserId("32743A38F38C226EE05312016B0A178A");
        proxySignVO.setProjectCode("002");
        proxySignVO.setCheckCode("yxwgrk");

        tx3102ReqVO.setHead(head);
        tx3102ReqVO.setProxySign(proxySignVO);

        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        String req = jsonObjectMapper.writeValueAsString(tx3102ReqVO);
        System.out.println("req:" + req);

        String userId = "38814918D6336888E05311016B0A0B19";
        String txCode = "3102";
        String signature = SecurityUtil.p7SignMessageDetach(HttpConnector.JKS_PATH, HttpConnector.JKS_PWD, HttpConnector.ALIAS, req);
        String res = httpConnector.post("platId/" + userId + "/txCode/" + txCode + "/transaction", req, signature);
        System.out.println("res:" + res);
    }
}
