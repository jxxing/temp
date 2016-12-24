package com.rd.ifaes.core.core.sign.cfca.test;

import cfca.sadk.algorithm.common.PKIException;
import cfca.trustsign.common.vo.cs.ContractVO;
import cfca.trustsign.common.vo.cs.HeadVO;
import cfca.trustsign.common.vo.request.tx3.Tx3210ReqVO;

import com.rd.ifaes.core.core.sign.cfca.connector.HttpConnector;
import com.rd.ifaes.core.core.sign.cfca.converter.JsonObjectMapper;
import com.rd.ifaes.core.core.sign.cfca.util.SecurityUtil;

public class Test3210 {
    public static void main(String[] args) throws PKIException {
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.init();

        Tx3210ReqVO tx3210ReqVO = new Tx3210ReqVO();
        HeadVO head = new HeadVO();
        head.setTxTime("20160102235959");

        ContractVO contract = new ContractVO();
        contract.setContractNo("QT20160413000000001");

        tx3210ReqVO.setHead(head);
        tx3210ReqVO.setContract(contract);

        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        String req = jsonObjectMapper.writeValueAsString(tx3210ReqVO);
        System.out.println("req:" + req);

        String userId = "9C9E731AEE444B498F7B5DCFBA0CD0E8";
        String txCode = "3210";
        String signature = SecurityUtil.p7SignMessageDetach(HttpConnector.JKS_PATH, HttpConnector.JKS_PWD, HttpConnector.ALIAS, req);
        String res = httpConnector.post("platId/" + userId + "/txCode/" + txCode + "/transaction", req, signature);
        System.out.println("res:" + res);
    }
}
