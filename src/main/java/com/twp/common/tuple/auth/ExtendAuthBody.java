package com.twp.common.tuple.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by tanweiping on 17/8/1.
 */
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ExtendAuthBody extends DefaultAuthBody{

    private String username;
    private String password;

    private String scope = "dfs";

    private String clientName = "dfs";

    /*
    scope:scope_secret base64编码
     */
    private String authorization = "Basic ZGZzOmRmc19zZWNyZXQ=";

    private String refresh_token = "";

    public void setDefaultAuthBody(DefaultAuthBody defaultAuthBody) {
        this.setGrant_type(defaultAuthBody.getLogin_grant_type());
        this.setContentTypePatch(defaultAuthBody.getContentTypePatch());
        this.clientName = this.scope;
    }
}
