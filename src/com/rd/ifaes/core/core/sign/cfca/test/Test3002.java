package com.rd.ifaes.core.core.sign.cfca.test;

import cfca.sadk.algorithm.common.PKIException;
import cfca.trustsign.common.vo.cs.EnterpriseTransactorVO;
import cfca.trustsign.common.vo.cs.EnterpriseVO;
import cfca.trustsign.common.vo.cs.HeadVO;
import cfca.trustsign.common.vo.request.tx3.Tx3002ReqVO;

import com.rd.ifaes.core.core.sign.cfca.connector.HttpConnector;
import com.rd.ifaes.core.core.sign.cfca.converter.JsonObjectMapper;
import com.rd.ifaes.core.core.sign.cfca.util.SecurityUtil;

public class Test3002 {
    public static void main(String[] args) throws PKIException {
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.init();

        Tx3002ReqVO tx3002ReqVO = new Tx3002ReqVO();
        HeadVO head = new HeadVO();
        head.setTxTime("20160102235959");

        EnterpriseVO enterprise = new EnterpriseVO();
        enterprise.setEnterpriseName("孙三的公司");
        enterprise.setIdentTypeCode("3");
        enterprise.setIdentNo("3301110017039721");
        enterprise.setMobilePhone("13735885335");
        enterprise.setLandlinePhone("11900139002");
        enterprise.setEmail("11900139002@cfca.com.cn");
        enterprise.setAuthenticationMode("公安部");

        EnterpriseTransactorVO enterpriseTransactor = new EnterpriseTransactorVO();
        enterpriseTransactor.setTransactorName("王五");
        enterpriseTransactor.setIdentTypeCode("1");
        enterpriseTransactor.setIdentNo("362321199112050011");
        enterpriseTransactor.setAddress("beijing");

        tx3002ReqVO.setHead(head);
        tx3002ReqVO.setEnterprise(enterprise);
        tx3002ReqVO.setEnterpriseTransactor(enterpriseTransactor);

        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        String req = jsonObjectMapper.writeValueAsString(tx3002ReqVO);
        System.out.println("req:" + req);

        String userId = "38814918D6336888E05311016B0A0B19";
        String txCode = "3002";
        String signature = SecurityUtil.p7SignMessageDetach(HttpConnector.JKS_PATH, HttpConnector.JKS_PWD, HttpConnector.ALIAS, req);
        String res = httpConnector.post("platId/" + userId + "/txCode/" + txCode + "/transaction", req, signature);
        System.out.println("res:" + res);
    }
}
