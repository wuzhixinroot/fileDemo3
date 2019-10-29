package com.wuzhixin.servlet;

import com.wuzhixin.rsasign.RSA;
import com.wuzhixin.rsasign.RSASignature;

public class Test {

    String path = "/Users/wuzhixin/oldIdeaLocation/idealocation/test/src/main/resources/privateKey.keystore";
    String path1 = "/Users/wuzhixin/oldIdeaLocation/idealocation/test/src/main/resources/publicKey.keystore";
    public static void main(String[] args) throws Exception {
        System.out.println("---------------私钥签名过程------------------");
        String content="ihep_这是用于签名的原始数据";
        String signstr= RSASignature.sign(content, RSA.loadPrivateKeyByFile(new Test().path),"UTF-8");
        System.out.println("签名原串："+content);
        System.out.println("签名串："+signstr);

        System.out.println("验签结果："+RSASignature.doCheck(content, signstr, RSA.loadPublicKeyByFile(new Test().path1),"UTF-8"));
        System.out.println();
    }
}
