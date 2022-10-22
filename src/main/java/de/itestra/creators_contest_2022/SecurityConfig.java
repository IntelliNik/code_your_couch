package de.itestra.creators_contest_2022;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SecurityConfig {
    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(
            final ClientRegistrationRepository clientRegistrationRepository,
            final OAuth2AuthorizedClientRepository authorizedClientRepository
    ) {
        final var authorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder()
                .clientCredentials()
                .build();

        final var authorizedClientManager = new DefaultOAuth2AuthorizedClientManager(
                clientRegistrationRepository, authorizedClientRepository);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        return authorizedClientManager;
    }

    @Bean
    public RestTemplate oAuthRestTemplate(
            final ClientRegistrationRepository clientRegistrationRepository,
            final OAuth2AuthorizedClientManager authorizedClientManager
    ) {
        var clientRegistration = clientRegistrationRepository.findByRegistrationId("keycloak");
        return new RestTemplateBuilder()
                .additionalInterceptors(new OAuthClientCredentialsRestTemplateInterceptor(
                        authorizedClientManager, clientRegistration))
                .build();
    }

    @Bean
    public WebSecurityCustomizer configure() throws Exception {
        return web -> web.ignoring().antMatchers("/**");
    }
}
