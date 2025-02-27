package net.catenax.sdhub.config;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.token.TokenManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;

@Configuration
public class KeycloakConfig {

    @Value("${keycloak.auth-server-url}")
    private String serverUrl;
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${app.custodianWallet.username}")
    private String username;
    @Value("${app.custodianWallet.password}")
    private String password;
    @Value("${app.custodianWallet.clientId}")
    private String clientId;
    @Value("${app.custodianWallet.clientSecret}")
    private String clientSecret;

    @Bean
    @ApplicationScope
    public TokenManager tokenManager() {
        var keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .username(username)
                .password(password)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(20).build())
                .build();
        return keycloak.tokenManager();
    }

}
