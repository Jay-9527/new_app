package com.depsystem.app.loginServer;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * loginMapper
 * @author adiao
 */
@Mapper
public interface LoginMapper extends BaseMapper<LoginDAO> {
    /** 查询用户【通过用户名】
     * @param name 用户名
     * @return 用户
     */
    @Select("select * from users where name = #{name}")
    LoginDAO findUserByName(@Param("name") String name);

    /** 查询用户
     * @param name 用户名
     * @param password 密码
     * @return 用户
     */
    @Select("select * from users where name = #{name} and password = #{password}")
    LoginDAO findUserByNameAndPassword(@Param("name") String name,@Param("password") String password);

    @Select("select * from users;")
    LoginDAO findUserAll();

    /**
     * 查询用户权限
     *
     * @param username 用户ID
     * @return string
     */
    @Results(id = "userPermission",value = {
            @Result(property = "permissionId",column = "permissionId"),
            @Result(property = "permission",column = "permission")
    })
    @Select("""
            select distinct m.permissionId,group_concat(p.name) as 'permission'
                    from users u
                             join roles r on u.rolesid = r.id
                             join permission p on r.id = p.rolesid
                             join menu m on p.operationid = m.permissionId
                    where u.name = #{username}
                    group by m.permissionId;""")
    List<Map<String, Object>> getUserPermissions(@Param("username") String username);

    /**
     * 查询用户模块路径
     * @param name 用户名
     * @return 数组
     */
    @Results(id = "userPath", value = {
            @Result(property = "name", column = "name"),
            @Result(property = "urls", column = "url")
    })
    @Select("""
            select distinct u.name, group_concat(distinct m.url) as url
                    from users u
                             join roles r on u.rolesid = r.id
                             join permission p on r.id = p.rolesid
                             join menu m on p.operationid = m.permissionId
                    where u.name = #{username};""")
    Map<String,Object> findUserByModelPath(@Param("username") String name);
}
