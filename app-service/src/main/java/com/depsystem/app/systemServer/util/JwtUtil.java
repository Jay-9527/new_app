/**
 * @author JOJO
 * @class JwtUtil
 * @date 2023/4/20
 * @apiNote
 */

package com.depsystem.app.systemServer.util;

import java.util.*;

import io.jsonwebtoken.*;

import static io.jsonwebtoken.Jwts.*;

public class JwtUtil {
    /**
     * 自定义密钥
     */
    private static final String SECRET_KEY = "NBNtndVbuixdd19h8L1YlYVW96GjRltY6uRGyPvNnjQ29rMOKm";

    /**
     * 动态生成token
     *  username 用户名
     *  roles 角色
     *  path 权限
     * @return token
     * setSubject 是加密的字段。
     * setIssuedAt是设置发布时间。
     * setExpiration 是有效时长。
     * setIssuedAt 是签发时间。
     */
    public static String generateToken(Map<String,Object> userinfo) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        return builder()
                .setClaims(userinfo)
                .setIssuedAt(now)
                .setExpiration(new Date(nowMillis + 3600000))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }


    /**
     * 解token
     *
     * @param token token
     */
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: " + e.getMessage());
        }

        return false;
    }

    /**
     * 获取token username
     * @param token token
     * @return username
     */
    public static String getUsernameFromToken(String token) {
        Claims claims = parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("name",String.class);
    }
    /**
     * 获取token username
     * @param token token
     * @return username
     */
    public static String getRoleFromToken(String token) {
        Claims claims = parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("role", String.class);
    }
    /**
     * 获取token username
     * @param token token
     * @return username
     */
    public static ArrayList<String> getPathFromToken(String token) {
        Claims claims = parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return (ArrayList<String>) Collections.singletonList(claims.get("path", String.class));
    }

}
