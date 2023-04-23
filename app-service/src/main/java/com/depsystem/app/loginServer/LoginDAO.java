/**
 * @author JOJO
 * @class LoginDAO
 * @date 2023/4/20
 * @apiNote
 */

package com.depsystem.app.loginServer;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("users")
@AllArgsConstructor
@NoArgsConstructor
public class LoginDAO {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String password;
    private String iphone;
    private String loginDate;
    private String loginNumber;
    private String createDate;
    private String updateDate;
}
