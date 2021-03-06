package com.rd.ifaes.core.core.sign.cfca.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cfca.sadk.algorithm.common.PKIException;
import cfca.trustsign.common.vo.cs.CreateContractVO;
import cfca.trustsign.common.vo.cs.HeadVO;
import cfca.trustsign.common.vo.cs.SignInfoVO;
import cfca.trustsign.common.vo.request.tx3.Tx3202ReqVO;

import com.rd.ifaes.core.core.sign.cfca.connector.HttpConnector;
import com.rd.ifaes.core.core.sign.cfca.converter.JsonObjectMapper;
import com.rd.ifaes.core.core.sign.cfca.util.SecurityUtil;

public class Test3202 {
    public static void main(String[] args) throws PKIException {
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.init();

        Tx3202ReqVO tx3202ReqVO = new Tx3202ReqVO();
        HeadVO head = new HeadVO();
        head.setTxTime("20160102235959");

        List<CreateContractVO> createContractlist = new ArrayList<CreateContractVO>();

        CreateContractVO createContract = new CreateContractVO();
        createContract.setTemplateId("1");

        Map<String, String> fieldMap = new HashMap<String, String>();
        fieldMap.put("text1", "2016");
        fieldMap.put("text2", "3");
        fieldMap.put("text3", "16");
        fieldMap.put("text4", "孙一");
        fieldMap.put("text5", "222321199112050001");
        fieldMap.put("text6", "成都");
        fieldMap.put("text7", "壹万");
        fieldMap.put("text8", "壹万元整");
        fieldMap.put("text9", "10000");
        fieldMap.put("text10", "壹万元整");
        fieldMap.put("text11", "10000");
        fieldMap.put("text12", "壹万元整");
        fieldMap.put("text13", "10000");
        fieldMap.put("text14", "叁万元整");
        fieldMap.put("text15", "30000");
        fieldMap.put("text16", "10000");
        fieldMap.put("text17", "10000");
        fieldMap.put("text18", "10000");
        fieldMap.put("text19", "孙一");
        fieldMap.put("text20", "成都支行");
        fieldMap.put("text21", "001");
        createContract.setInvestmentInfo(fieldMap);

        SignInfoVO[] signInfos = new SignInfoVO[1];
        SignInfoVO signInfoVO0 = new SignInfoVO();
        signInfoVO0.setUserId("6327987D3B854A3A9F4A198426084F02");
        signInfoVO0.setIsProxySign(1);
        signInfoVO0.setLocation("成都");
        signInfoVO0.setProjectCode("002");
        signInfoVO0.setSignLocation("Signature1");
        signInfoVO0.setAuthorizationTime("20160214171200");
        signInfos[0] = signInfoVO0;
        createContract.setSignInfos(signInfos);

        CreateContractVO createContract2 = new CreateContractVO();
        createContract2.setTemplateId("1");
        createContract2.setInvestmentInfo(fieldMap);
        createContract2.setSignInfos(signInfos);

        createContractlist.add(createContract);
        createContractlist.add(createContract2);

        tx3202ReqVO.setHead(head);
        tx3202ReqVO.setBatchNo("B112");
        tx3202ReqVO.setCreateContracts(createContractlist.toArray(new CreateContractVO[0]));

        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        String req = jsonObjectMapper.writeValueAsString(tx3202ReqVO);
        System.out.println("req:" + req);

        String userId = "9C9E731AEE444B498F7B5DCFBA0CD0E8";
        String txCode = "3202";
        String signature = SecurityUtil.p7SignMessageDetach(HttpConnector.JKS_PATH, HttpConnector.JKS_PWD, HttpConnector.ALIAS, req);
        String res = httpConnector.post("platId/" + userId + "/txCode/" + txCode + "/transaction", req, signature);
        System.out.println("res:" + res);
    }
}
