package cn.yz.sm2;

import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.util.encoders.Hex;

import java.security.KeyPair;

/**
 * Sm2工具类测试
 * 使用方法：
 * 1、引入maven依赖 https://mvnrepository.com/artifact/org.bouncycastle/bcpkix-jdk15on
 * 2、生成密钥对，也可使用在线地址生成 https://const.net.cn/tool/sm2/genkey/
 * 3、开始加解密
 * @author yuze
 * @date 2023/8/9
 */
public class SmDemo {

    public static void main(String[] args) {
        // 生成公钥和私钥
        KeyPair keyPair = SM2Utils.createECKeyPair();
        BCECPublicKey publicKey = (BCECPublicKey) keyPair.getPublic();
        String hexPublicKey = Hex.toHexString(publicKey.getQ().getEncoded(false));
        BCECPrivateKey privateKey = (BCECPrivateKey) keyPair.getPrivate();
        String hexPrivateKey = privateKey.getD().toString(16);
        System.out.println(hexPublicKey);
        System.out.println(hexPrivateKey);
        // 使用Sm2Utils加密/解密
        String aa = SM2Utils.encrypt(hexPublicKey, "ceshi123132", 0);
        System.out.println("加密后："+aa);
        System.out.println("解密后："+SM2Utils.decrypt(hexPrivateKey, aa, 0));

        // 使用Sm2Utils生成的密钥测试第三方开源工具类 https://github.com/antherd/sm-crypto
        /*String publicKey = "040ed43f16e08f6d5021f42ad4833d2218ab2587d445225999c77e8180322c8d49f3659f455893f34642fcb55fb3bbeb86743e5b779085c70b2ef7727941b7e36b";
        String privateKey = "5846927142710c7b74804a68cca619f949d7697c591b1cebafd2676683179d1d";

        // 测试通过，也是可以使用的
        String doEncrypt = Sm2.doEncrypt("haha", publicKey, 0);
        System.out.println(doEncrypt);
        String doDecrypt = Sm2.doDecrypt(doEncrypt, privateKey, 0);
        System.out.println(doDecrypt);*/


    }


}
