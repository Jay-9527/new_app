/**
 * @author JOJO
 * @class CaptchaVerifyException
 * @date 2023/4/21
 * @apiNote
 */

package com.depsystem.app.systemServer.securityServer.exception;

import org.springframework.security.core.AuthenticationException;

public class CaptchaVerifyException extends AuthenticationException {
    public CaptchaVerifyException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CaptchaVerifyException(String msg) {
        super(msg);
    }
}
