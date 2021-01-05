package com.enough.oauth2sso.auth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;

/**
 * @program: SpringBoot-Demo4Learn
 * @description: 授权认证服务配置类
 * @author: lidong
 * @create: 2020/04/26
 */
@Configuration
// 开启授权服务
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //父类方法都是空实现
        // 允许客户端认证
        security.allowFormAuthenticationForClients();
        // 开启/oauth/token_key验证端口无权限访问
        security.tokenKeyAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 配置jdbc数据源,连接mysql数据库
        clients.jdbc(dataSource);
        //        clients.inMemory()
        //                .withClient("clientapp")
        //                .secret("123456")
        //                .redirectUris("http://localhost:9000/callback")
        //                //密码模式
        //                .authorizedGrantTypes("password")
        //                .scopes("read_profile", "read_contacts");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //设置token转换器：使用JWT
        endpoints.accessTokenConverter(jwtAccessTokenConverter());
        // 设置token持久化接口对象：使用JWT
        endpoints.tokenStore(jwtTokenStore());
    }

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        //设置用于签署JWT的密钥
        jwtAccessTokenConverter.setSigningKey("enoughtoken");
        return jwtAccessTokenConverter();
    }
}
