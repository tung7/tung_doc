package com.tung7.docsys.shiro;

import com.tung7.docsys.support.utils.Digests;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * 自定义shiro 登录验证的校验类。
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/1.
 * @update
 */
public class CustomCredentialsMatcher extends HashedCredentialsMatcher {
    public CustomCredentialsMatcher() {
        super();
    }

    public CustomCredentialsMatcher(String algorithmName) {
        super(algorithmName);
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String dbSalt = ((SaltedAuthenticationInfo)info).getCredentialsSalt().toHex();
        Object tokenCredentials = encrypt(String.valueOf(token.getPassword()), dbSalt);
        Object accountCredentials = getCredentials(info);
        //将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false
        // accountCredentials是SimpleHash类型，需要转成string。才能正确比较。
        return equals(tokenCredentials, accountCredentials.toString());
    }

    //将传进来的密码进行加密
    private String encrypt(String data, String dbSalt) {
        String algorithName = getHashAlgorithmName();
        int iteration = getHashIterations();
        byte[] encryptPassBytesToCompare = Digests.digest(data.getBytes(),
                algorithName,
                Digests.decodeHex(dbSalt),
                iteration);
        return Digests.encodeHex(encryptPassBytesToCompare);
    }
}
