package com.blog.cloud.wxa;


import com.google.common.base.Charsets;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Key;

public class UserInfoUtil {

    private final String sessionKey;

    public UserInfoUtil(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    /**
     * AES解密
     *
     * @param encryptedData 密文
     * @param ivStr         iv
     * @return {String}
     */
    public String decrypt(String encryptedData, String ivStr) {
        try {
            byte[] bizData = EncryptUtil.decryptBASE64(encryptedData);
            byte[] keyByte = EncryptUtil.decryptBASE64(sessionKey);
            byte[] ivByte = EncryptUtil.decryptBASE64(ivStr);
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            Key sKeySpec = new SecretKeySpec(keyByte, "AES");
            // 初始化
            AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
            params.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, params);
            byte[] original = cipher.doFinal(bizData);
            // 去除补位字符
            byte[] result = Pkcs7Encoder.decode(original);
            return new String(result, Charsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("aes解密失败");
        }
    }
}