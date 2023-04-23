/**
 * @author JOJO
 * @class myUserDetails
 * @date 2023/4/22
 * @apiNote
 */

package com.depsystem.app.systemServer.securityServer.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.depsystem.app.loginServer.Login;
import jakarta.annotation.Nullable;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Data
@NoArgsConstructor
public class MyUserDetails implements UserDetails {
    @Resource
    Login login;
    private String roles;
    private List<String> path;
    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean isEnabled;

    public MyUserDetails(Login loginVO, String roles, List<String> paths) {

        this(loginVO,roles,paths,true,true,true,true);
    }

    public MyUserDetails(Login loginVO, String roles, List<String> paths, boolean b, boolean b1, boolean b2, boolean b3) {
        this.login = loginVO;
        this.roles = roles;
        this.path = paths;
        this.accountNonExpired = b;
        this.accountNonLocked = b1;
        this.credentialsNonExpired = b2;
        this.isEnabled = b3;
    }

    public String getRoles() {
        return roles;
    }
    public List<String> getPath() {
        return this.path;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (ObjectUtils.isEmpty(authorities))
        {
            return authorities;
        }
        SimpleGrantedAuthority sb = new SimpleGrantedAuthority(roles);
        authorities = Collections.singletonList(sb);
        return authorities;
    }

    @Override
    public String getPassword() {
        return login.getPassword();
    }

    @Override
    public String getUsername() {
        return login.getName();
    }

    /** 实现帐户是否过期的逻辑
     * @return Boolean
     */
    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    /** 实现帐户是否被锁定的逻辑
     * @return Boolean
     */
    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    /** 实现凭证是否过期的逻辑
     * @return Boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    /** 实现帐户是否启用的逻辑
     * @return Boolean
     */
    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
