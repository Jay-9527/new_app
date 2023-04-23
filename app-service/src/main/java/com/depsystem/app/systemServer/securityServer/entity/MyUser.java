/**
 * @author JOJO
 * @class MyUser
 * @date 2023/4/23
 * @apiNote
 */

package com.depsystem.app.systemServer.securityServer.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MyUser extends User{
    public MyUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
