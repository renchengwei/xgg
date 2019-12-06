
package com.xgg.auth.authentication.extension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * token注销服务，通过此服务可以删除已经生效的token信息
 * @author renchengwei
 * @date 2019-11-16
 *
 */

@FrameworkEndpoint
public class RevokeTokenEndpoint {

    @Autowired
    ConsumerTokenServices consumerTokenServices;

    @RequestMapping("/oauth/revoke_token")
    @ResponseBody
    public ResponseEntity<?> revokeToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.contains("Bearer")){
            String tokenId = authorization.substring("Bearer".length()+1);
            consumerTokenServices.revokeToken(tokenId);
        }
        return ResponseEntity.ok("token revoke success");
    }

}

