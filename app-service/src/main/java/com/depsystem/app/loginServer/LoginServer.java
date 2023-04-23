package com.depsystem.app.loginServer;

/** login server
 * @author adiao
 */
public interface LoginServer {
    /** 登录
     * @param name 用户名
     * @param password 密码
     * @return VO
     */
    Login login(String name,String password);

    /** 注册
     * @param login VO
     * @return VO
     */
    Login register(Login login);
}
