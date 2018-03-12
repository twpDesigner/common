package com.twp.common.tuple.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by tanweiping on 17/8/12.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PasswordReset {

    private String account;

    //验证码
    private String validateCode;

    //新密码
    private String newPassword;

}
