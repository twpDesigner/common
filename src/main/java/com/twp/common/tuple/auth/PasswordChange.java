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
public class PasswordChange {

    private String userName;

    //原密码
    private String oldPassword;

    //新密码
    private String newPassword;

}
