package com.danicalifornia.authserver.config;

import com.danicalifornia.authserver.models.User;
import com.danicalifornia.authserver.services.IUsersService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
public class CustomTokenEnhancer implements TokenEnhancer {

    private final IUsersService usersService;

    private final CacheManager cacheManager;

    @Value("${oauth.gre.id}")
    private String greId;

    @Value("${oauth.quick-pay.id}")
    private String quickPayId;

    public CustomTokenEnhancer(IUsersService usersService,
                               CacheManager cacheManager) {

        this.usersService = usersService;
        this.cacheManager = cacheManager;
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        User user = usersService.findEmployerUserByUsername(authentication.getName());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(Map.of(
                "id", user.getId(),
                "fullName", user.getFullName()
        ));

        Objects.requireNonNull(cacheManager.getCache("user")).clear();

        return accessToken;
    }

}
