/**
 * @author JOJO
 * @class CaptchaVO
 * @date 2023/4/21
 * @apiNote
 */

package com.depsystem.app.loginServer;

import lombok.Data;

import java.io.Serializable;
@Data
public class CaptchaVO implements Serializable {
    private String id;
    private String base64;
}
