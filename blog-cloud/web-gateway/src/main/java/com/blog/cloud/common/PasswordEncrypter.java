package com.blog.cloud.common;

import com.blog.cloud.pojo.user.BlogUser;
import lombok.Data;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

@Data
public class PasswordEncrypter<T extends BlogUser> {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    // 散列算法
    private String algorithmName = "md5";
    // 散列次数
    private int hashIterations = 2;

    public void encryptPassword(T t) {
        // 随机生成盐
        t.setSalt(randomNumberGenerator.nextBytes().toHex());
        // 加密
        String encryptPwd = new SimpleHash(algorithmName, t.getPassword(),
                ByteSource.Util.bytes(t.getSalt()), hashIterations).toHex();
        t.setPassword(encryptPwd);
    }

}