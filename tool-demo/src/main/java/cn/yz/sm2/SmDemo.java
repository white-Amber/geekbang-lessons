package cn.yz.sm2;

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
        /*KeyPair keyPair = SM2Utils.createECKeyPair();
        BCECPublicKey publicKey = (BCECPublicKey) keyPair.getPublic();
        String hexPublicKey = Hex.toHexString(publicKey.getQ().getEncoded(false));
        BCECPrivateKey privateKey = (BCECPrivateKey) keyPair.getPrivate();
        String hexPrivateKey = privateKey.getD().toString(16);
        System.out.println(hexPublicKey);
        System.out.println(hexPrivateKey);
        // 使用Sm2Utils加密/解密
        String aa = SM2Utils.encrypt(hexPublicKey, "ceshi123132", 0);
        System.out.println("加密后："+aa);
        System.out.println("解密后："+SM2Utils.decrypt(hexPrivateKey, aa, 0));*/

        // 使用Sm2Utils生成的密钥测试第三方开源工具类 https://github.com/antherd/sm-crypto
        /*String publicKey = "040ed43f16e08f6d5021f42ad4833d2218ab2587d445225999c77e8180322c8d49f3659f455893f34642fcb55fb3bbeb86743e5b779085c70b2ef7727941b7e36b";
        String privateKey = "5846927142710c7b74804a68cca619f949d7697c591b1cebafd2676683179d1d";

        // 测试通过，也是可以使用的
        String doEncrypt = Sm2.doEncrypt("haha", publicKey, 0);
        System.out.println(doEncrypt);
        String doDecrypt = Sm2.doDecrypt(doEncrypt, privateKey, 0);
        System.out.println(doDecrypt);*/

        // 测试 sm-crpto加密，Sm2Utils解密
//        String publicKey = "04ce4f55f34316335678056730b21d80ee49e38ac67f231e8f7bf19eb41b8c2c36e702f51cfb3df3afc43ffb8981ff0680db62a819e216febf9af369669e741cac";
//        String privateKey = "f0fbf37b55d0feaa2ecf9048a66f51f777d8c9b02dc5fa7c800a128a4c7f3850";
//        String publicKey = "2811CE6181A790C6E3F06278B66F35B069BFB91A88FE11276ACAF51CB67CEC6AA85241EA8EDD2C7B3E8CCD5C95B4AA03D7D30C5FB29D78117643E4C004080B72";
//        String privateKey = "26F7B82FAEEE0D391E48ECA4D1E2E5B18A51EFC2F55511291151056A9AA4E7CC";
//        String ming = "6949,1,42506408500,1691649275243";
//        String mi = "480d057c3a030a53966f458a07eed04aef6f2656042bc8ae7f13fb95b7a7b4a39a009fe599caae34de8be86a60c0fea91d8fcb60baf460d1d9eb0190a8bece6b51262d8060683164d0682a6361e3080c6a753c3d9ce230c2746aba6efb6dd713fa561aed9ee81a2106c60d0967ad09650c7be183db2b3fc151991b96420606e717d245";
//        String doDecrypt = Sm2.doDecrypt(mi, privateKey, 0);
//        System.out.println(doDecrypt);
//        String enc = Sm2.doEncrypt("hello sm2", publicKey, 0);
//        String enc = SM2Utils.encrypt(publicKey, "hello sm2", 0);
//        System.out.println(enc);
//        String doDecrypt = Sm2.doDecrypt(enc, privateKey, 0);
//        String doDecrypt = SM2Utils.decrypt(privateKey, enc, 0);
//        System.out.println(doDecrypt);

        /*Keypair keypair = Sm2.generateKeyPairHex();
        String publicKey = keypair.getPublicKey();
        String privateKey = keypair.getPrivateKey();
        System.out.println(publicKey);
        System.out.println(privateKey);*/

        // 测试his（李）加密的参数，使用Sm2Utils工具类解密
        String publicKey  = "04ce4f55f34316335678056730b21d80ee49e38ac67f231e8f7bf19eb41b8c2c36e702f51cfb3df3afc43ffb8981ff0680db62a819e216febf9af369669e741cac";
        String privateKey = "f0fbf37b55d0feaa2ecf9048a66f51f777d8c9b02dc5fa7c800a128a4c7f3850";
//        String publicKey  = "040c8785cef459c9a24fb9b542bbe56ceb525e36f7f11b48a1f20d00ba5f6028dfdab7e4326e5c3cadee3a89a08e999731b2e7eda832ef43dac58f2fba5a0fb7a8";
//        String privateKey = "e3d220d056e8c566ce953bbb8fc6e2e250f1cbc4945a9c3dd8848bd3b79e3a99";
        String miwen = "04566f6e172c34dc994abc06c6b5353ddd83074d3996929d1f627bbf48b9a165e6f4d7101d2cc5f47bdb9c3aa27be3b37403deb684beba80d60711da491fd8d467fd62f3c7faac34a9549246983ba2075cd2f57ba489ce9cfb34fc0a35c6bab23cf2b47b81b929d51112b8f752175c7d38d8118cd7484f5db7da0928af6dd673d7";
//        String miwenqianduan = "04718305b2a71fa8cd91befc2190b6f2a8b5bc44aa643ebf6730c1b62121ec5d21c26c24bb3400b8e69e5756dfcc5301781c6ee7dc694b9a430c02862aae2813538aaa0047bb0867e0723c400f5feb963169cc3bc6bd72f5fc9299151854ba2936fbbbaf5fdcdf509207cad7dad8fc8ae4d9c8057fbe68cffe6d5be310";
//        String miwenYaoyao = "04C1841ECCEEBA8AC599CAA03928CBBE86F94122D9E3A67A2DBB499CA4D6CDB1FA27CD57BB5632290F12852AC99DA41C0D59215BAE66162F0D6106A975B1D73BDADADA1E9379EE18DF68D0EB86B77B41EED4A168061A5154135657F3AD3509B56B01EA8FD2D4C51245D77863651A3ADEC9E4C56F5D8252CE4248A9A2AE7D4A6699A5F0613FE0ADCAA202";
        String decrypt = SM2Utils.decrypt(privateKey, miwen, 0);
        System.out.println(decrypt);

//        String encrypt = SM2Utils.encrypt(publicKey, "hello sm2", 0);
//        System.out.println(encrypt);
//        String decrypt = SM2Utils.decrypt(privateKey, encrypt, 0);
//        System.out.println(decrypt);


    }


}
