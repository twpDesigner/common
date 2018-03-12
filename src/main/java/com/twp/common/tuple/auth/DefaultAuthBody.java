package com.twp.common.tuple.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor@Data@AllArgsConstructor@Builder
public class DefaultAuthBody {

    private String grant_type;

    @Builder.Default
    private String login_grant_type = "password";
    @Builder.Default
    private String refresh_grant_type="refresh_token";
    @Builder.Default
    private String contentTypePatch="application/x-www-form-urlencoded";
}
