package com.danicalifornia.authserver.config;

import com.danicalifornia.authserver.services.CustomTokenService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@RefreshScope
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final BCryptPasswordEncoder passwordEncoder;

    private final CustomTokenEnhancer customTokenEnhancer;

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.access-token.expiration-time}")
    private int accessTokenExpirationTime;

    @Value("${jwt.refresh-token.expiration-time}")
    private int refreshTokenExpirationTime;

    @Value("${oauth.frontend.id}")
    private String greId;

    @Value("${oauth.frontend.secret}")
    private String greSecret;

    @Value("${oauth.zuul.id}")
    private String zuulClientId;

    @Value("${oauth.zuul.secret}")
    private String zuulSecret;

    public AuthorizationServerConfig(BCryptPasswordEncoder passwordEncoder,
                                     AuthenticationManager authenticationManager,
                                     CustomTokenEnhancer customTokenEnhancer,
                                     @Qualifier("usersService") UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.customTokenEnhancer = customTokenEnhancer;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(greId)
                .secret(passwordEncoder.encode(greSecret))
                .scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token")
                .and()
                .withClient(zuulClientId)
                .secret(passwordEncoder.encode(zuulSecret))
                .scopes("read", "write")
                .authorizedGrantTypes("refresh_token");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(customTokenEnhancer, accessTokenConverter()));

        CustomTokenService tokenService = new CustomTokenService();
        tokenService.setTokenStore(tokenStore());
        tokenService.setSupportRefreshToken(true);
        tokenService.setTokenEnhancer(tokenEnhancerChain);
        tokenService.setAccessTokenValiditySeconds(accessTokenExpirationTime);
        tokenService.setRefreshTokenValiditySeconds(refreshTokenExpirationTime);

        endpoints
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter())
                .userDetailsService(userDetailsService)
                .tokenServices(tokenService)
                .tokenEnhancer(tokenEnhancerChain);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey(secretKey);
        tokenConverter.setVerifierKey(secretKey);
        return tokenConverter;
    }
}
