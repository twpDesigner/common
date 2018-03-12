package com.twp.common.tuple.auth;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Delegate;

/**
 * Created by tanweiping on 17/8/1.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RefreshBody extends DefaultAuthBody{
    private String authorization = "Basic dWk6dWlfc2VjcmV0";
    private String clientName;
    private String scope;
    private String refresh_token;


    public void setDefaultAuthBody(DefaultAuthBody defaultAuthBody) {
        this.setGrant_type(defaultAuthBody.getRefresh_grant_type());
        this.setContentTypePatch(defaultAuthBody.getContentTypePatch());
        this.clientName = this.scope;
    }
}
