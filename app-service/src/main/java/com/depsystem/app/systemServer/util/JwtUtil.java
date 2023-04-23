/**
 * @author JOJO
 * @class JwtUtil
 * @date 2023/4/20
 * @apiNote
 */

package com.depsystem.app.systemServer.util;

import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.SignatureAlgorithm;
import static io.jsonwebtoken.Jwts.*;

public class JwtUtil {
    /**
     * 自定义密钥
     */
    private static final String SECRET_KEY = "NBNtndVbuixdd19h8L1YlYVW96GjRltY6uRGyPvNnjQ29rMOKm";

    /**
     * 动态生成token
     * @param username 用户名
     * @param roles 角色
     * @param permission 权限
     * @return token
     * setSubject 是加密的字段。
     * setIssuedAt是设置发布时间。
     * setExpiration 是有效时长。
     * setIssuedAt 是签发时间。
     */
    public static String generateToken(String username,String roles,String permission) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        return builder()
                .setSubject(username)
                .setSubject(roles)
                .setSubject(permission)
                .setIssuedAt(now)
                .setExpiration(new Date(nowMillis + 3600000))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }


    /**
     * 解token
     * @param token token
     * @return 返回
     */
    public static Claims validateToken(String token) {
        Jws<Claims> claimsJws = parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        return claimsJws.getBody();
    }

    /**
     * 获取token username
     * @param token token
     * @return username
     */
    public static String getUsernameFromToken(String token) {
        Claims claims = parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("username").toString();
    }
    /**
     * 获取token username
     * @param token token
     * @return username
     */
    public static String getRoleFromToken(String token) {
        Claims claims = parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("roles").toString();
    }
    /**
     * 获取token username
     * @param token token
     * @return username
     */
    public static String getPermissionFromToken(String token) {
        Claims claims = parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("permission").toString();
    }

}
