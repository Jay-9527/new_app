/**
 * @author JOJO
 * @class JwtUtil
 * @date 2023/4/20
 * @apiNote
 */

package com.depsystem.app.systemServer.util;

import java.util.*;

import com.depsystem.app.loginServer.Login;
import com.depsystem.app.systemServer.securityServer.entity.MyUserDetails;
import com.depsystem.app.systemServer.util.securityUtil.ETLUtil;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

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
    public static String generateToken(MyUserDetails userinfo) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Claims claims = Jwts.claims().setSubject(userinfo.getUsername());
        claims.put("username",userinfo.getUsername());
        claims.put("userroles",userinfo.getRoles());
        claims.put("path",userinfo.getPath());
        return builder()
                .setClaims(claims)
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
    public static Authentication validateToken(String token) {
            Jws<Claims> claimsJws = parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            claimsJws.getBody();
            Claims body = claimsJws.getBody();
            String username = body.get("username").toString();
            String userroles = body.get("userroles").toString();
            Object path = body.get("path");
            List<String> strings = ETLUtil.parseArrayToList(path);
            MyUserDetails userDetails = new MyUserDetails();
            Login login = new Login();
            login.setName(username);
            userDetails.setRoles(userroles);
            userDetails.setPath(strings);
            return new UsernamePasswordAuthenticationToken(userDetails, "");
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
