package com.excellent.accreditation.common.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.excellent.accreditation.common.domain.Const;
import com.excellent.accreditation.untils.EncryptUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

@Slf4j
public class JWTUtil {

    /**
     * @Author 安羽兮
     * @Description token过期时间 单位 秒
     * @Date 12:48 2019/12/7
     **/
    private static final long EXPIRE_TIME = 1000 * 60 * 60;

    /**
     * 校验 token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String name, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("name", name)
                    .build();
            verifier.verify(token);
            log.info("token is valid");
            return true;
        } catch (Exception e) {
            log.info("token is invalid{}", e.getMessage());
            return false;
        }
    }

    /**
     * 从 token中获取用户名称
     *
     * @return token中包含的用户名称
     */
    public static String getName(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("name").asString();
        } catch (JWTDecodeException e) {
            log.error("error：{}", e.getMessage());
            return null;
        }
    }

    /**
     * 生成 token
     *
     * @param name   用户名称
     * @param secret 用户的密码
     * @return token
     */
    public static String sign(String name, String secret) {
        try {
            name = StringUtils.lowerCase(name);
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withClaim("name", name)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            log.error("error：{e}", e);
            return null;
        }
    }


    /**
     * token 加密
     *
     * @param token token
     * @return 加密后的 token
     */
    public static String encryptToken(String token) {
        try {
            EncryptUtils encryptUtils = new EncryptUtils(Const.TOKEN_CACHE_PREFIX);
            return encryptUtils.encrypt(token);
        } catch (Exception e) {
            log.info("token加密失败：", e);
            return null;
        }
    }

    /**
     * token 解密
     *
     * @param encryptToken 加密后的 token
     * @return 解密后的 token
     */
    public static String decryptToken(String encryptToken) {
        try {
            EncryptUtils encryptUtils = new EncryptUtils(Const.TOKEN_CACHE_PREFIX);
            return encryptUtils.decrypt(encryptToken);
        } catch (Exception e) {
            log.info("token解密失败：", e);
            return null;
        }
    }
}
