package com.weitw.study.sbt.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.UUID;

public class AESUtil {
	/**
     * AES加解密
     */
    private static final String ALGORITHM = "AES";

    /**
     * 默认加密的KEY
     */
    private static final String KEY_DEFAULT = "xinshiyun2019%&*";

    /**
     * 工作模式：ECB
     */
    private static final String TRANSFORM_ECB_PKCS5 = "AES/ECB/PKCS5Padding";

    public static String encryptEcbMode(String value) {
        return encryptEcbMode(value, KEY_DEFAULT);
    }

    static final Logger logger = LoggerFactory.getLogger(AESUtil.class);
 
    /**
     * 基于ECB工作模式的AES加密
     * @author zifangsky
     * @date 2018/8/14 11:42
     * @since 1.0.0
     * @param value 待加密字符串
     * @param key 秘钥，如果不填则使用默认值
     * @return java.lang.String
     */
    public static String encryptEcbMode(final String value, String key){
        if(StringUtils.isNotBlank(value)){
            //如果key为空，则使用默认值
            if(key == null ){
                key = KEY_DEFAULT;
            }
 
            //密码
            final SecretKeySpec keySpec = getSecretKey(key);
 
            try {
                Cipher encipher = Cipher.getInstance(TRANSFORM_ECB_PKCS5);
 
                //加密模式
                encipher.init(Cipher.ENCRYPT_MODE, keySpec);
                //使用AES加密
                byte[] encrypted = encipher.doFinal(getUTF8Bytes(value));
                //然后转成BASE64返回
                return Base64.encodeBase64String(encrypted);
            } catch (Exception e) {
                logger.error(MessageFormat.format("基于ECB工作模式的AES加密失败,VALUE:{0},KEY:{1}",value,key));
            }
        }
 
        return null;
    }

    public static String decryptEcbMode(String encryptedStr) {
        return decryptEcbMode(encryptedStr, KEY_DEFAULT);
    }

    /**
     * 解密，如果解密失败，则返回原字符串。电话通知以及某些表存在部分数据加密，部分未加密的情况，如果用原来的方法，无法满足场景
     * @param encryptedStr
     * @return
     */
    public static String decryptEcbModeWithException(String encryptedStr) {
        try {
            return decryptEcbMode(encryptedStr, KEY_DEFAULT);
        } catch (Exception e) {
            logger.info("AES解密失败，返回原字符串,e=", e);
            return encryptedStr;
        }
    }
 
    /**
     * 基于ECB工作模式的AES解密
     * @author zifangsky
     * @date 2018/8/14 11:42
     * @since 1.0.0
     * @param encryptedStr AES加密之后的字符串
     * @param key 秘钥，如果不填则使用默认值
     * @return java.lang.String
     */
    public static String decryptEcbMode(final String encryptedStr, String key){
        if(StringUtils.isNotBlank(encryptedStr)){
            //如果key为空，则使用默认值
            if(key == null){
                key = KEY_DEFAULT;
            }
 
            //密码
            final SecretKeySpec keySpec = getSecretKey(key);
 
            try {
                Cipher encipher = Cipher.getInstance(TRANSFORM_ECB_PKCS5);
 
                //加密模式
                encipher.init(Cipher.DECRYPT_MODE, keySpec);
                //先用BASE64解密
                byte[] encryptedBytes = Base64.decodeBase64(encryptedStr);
                //然后再AES解密
                byte[] originalBytes = encipher.doFinal(encryptedBytes);
                //返回字符串
                return new String(originalBytes);
            } catch (Exception e) {
                logger.error(MessageFormat.format("基于ECB工作模式的AES解密失败,encryptedStr:{0},KEY:{1}",encryptedStr,key));
            }
        }
 
        return null;
    }
 
    /**
     * 将字符串转化为UTF8格式的byte数组
     *
     * @param input the input string
     * @return UTF8 bytes
     */
    private static byte[] getUTF8Bytes(String input) {
        return input.getBytes(StandardCharsets.UTF_8);
    }
 
    /**
     * 生成加密秘钥
     * @param KEY 明文秘钥
     * @return SecretKeySpec
     */
    private static SecretKeySpec getSecretKey(final String KEY) {
//        //生成指定算法密钥
//        KeyGenerator generator = null;
// 
//        try {
//            generator = KeyGenerator.getInstance(ALGORITHM);
// 
//          SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
//          random.setSeed(getUTF8Bytes(KEY));
//          //指定AES密钥长度（128、192、256）
//            generator.init(128,random);
////            generator.init(128, new SecureRandom(getUTF8Bytes(KEY)));
//            //生成一个密钥
//            SecretKey secretKey = generator.generateKey();
//            //转换为AES专用密钥
//            return new SecretKeySpec(secretKey.getEncoded(), ALGORITHM);
//            
//        } catch (Exception ex) {
//            System.out.println(MessageFormat.format("生成加密秘钥失败,KEY:{0}",KEY));
//            ex.printStackTrace();
//        }
 
          return new SecretKeySpec(getUTF8Bytes(KEY), ALGORITHM);
    }

    public static String base64(String uid,String key){
        if(StringUtils.isBlank(key) || StringUtils.isBlank(uid)  ){
            return  null;
        }
        long timestamp = System.currentTimeMillis() / 1000;
        String encryStr = "{\"timestamp\":"+timestamp+",\"uid\":\""+uid+"\"}";
        String token = AESUtil.encryptEcbMode(encryStr, key);
        return token;
    }
 
    public static void main(String[] args) throws UnsupportedEncodingException {
//        String encryptedStr = AESUtil.encryptCbcMode("15850569649", "xinshiyun20190715*=%=", null);
//        System.out.println(encryptedStr);
// 
//        String originalStr = AESUtil.decryptCbcMode(encryptedStr, "xinshiyun20190715*=%=", null);
//        System.out.println(originalStr);
        System.out.println(AESUtil.encryptEcbMode("123456"));
        System.out.println(AESUtil.decryptEcbMode("8fSVtfenwcJIjtp8OfAj1Q=="));
 
        String encryptedStr2 = AESUtil.encryptEcbMode("", null);
        System.out.println("encryptedStr2: "+encryptedStr2);
        if(StringUtils.isNotEmpty(encryptedStr2)){
            System.out.println("Thats a const");
        }
        String originalStr2 = AESUtil.decryptEcbMode("GQV0UDiPJdcZ3haN62WLKi8s2gko4xOEK0eMxRsvaBNZJq9N0ZtXiW42FT4SH9M0KKSuFRZiVht1955PZzEP5k4U2Sbq+m2UaYrSX8Qihtk=", "xinshiyun19gz%&*");
        System.out.println(originalStr2);
        System.out.println(URLEncoder.encode("GQV0UDiPJdcZ3haN62WLKtSUtJeRG+45AQAyxhhe+CpZJq9N0ZtXiW42FT4SH9M0KKSuFRZiVht1955PZzEP5k4U2Sbq+m2UaYrSX8Qihtk=", "utf-8"));
        System.out.println(UUID.randomUUID());
        System.out.println(encryptEcbMode("{\"timestamp\":\"1712567234\",\"czrId\":\"abcd12345\"}"));
 
    }
}
