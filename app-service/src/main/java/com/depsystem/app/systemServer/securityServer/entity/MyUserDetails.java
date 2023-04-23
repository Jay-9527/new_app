/**
 * @author JOJO
 * @class myUserDetails
 * @date 2023/4/22
 * @apiNote
 */

package com.depsystem.app.systemServer.securityServer.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.depsystem.app.loginServer.Login;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class MyUserDetails implements UserDetails {
    @Resource
    Login login;
    private List<String> permissions;
    private String roles;
    private List<String> path;
    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;
    public String getRoles() {
        return roles;
    }
    public List<String> getPath() {
        return this.path;
    }
    public MyUserDetails(Login login, String roles, List<String> permissions,List<String> paths) {
        this.login=login;
        this.permissions = permissions;
        this.roles=roles;
        this.path=paths;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (ObjectUtils.isEmpty(authorities))
        {
            return authorities;
        }
        authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
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
        return false;
    }

    /** 实现帐户是否被锁定的逻辑
     * @return Boolean
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /** 实现凭证是否过期的逻辑
     * @return Boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /** 实现帐户是否启用的逻辑
     * @return Boolean
     */
    @Override
    public boolean isEnabled() {
        return false;
    }
}
