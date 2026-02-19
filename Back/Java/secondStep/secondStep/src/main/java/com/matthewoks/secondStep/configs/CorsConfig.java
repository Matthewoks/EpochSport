package com.matthewoks.secondStep.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

/**
 * Configurazione CORS per permettere le chiamate dal frontend Angular
 * in esecuzione su http://localhost:4200.
 *
 * Aggiungere questa classe al package configs del backend.
 * In produzione: sostituire l'origin con il dominio reale.
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // Origini consentite (dev: Angular locale | prod: sostituire con dominio reale)
        config.setAllowedOrigins(List.of(
                "http://localhost:4200",
                "http://localhost:4300"  // porta alternativa per test
        ));

        // Metodi HTTP consentiti
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));

        // Header consentiti nelle richieste
        config.setAllowedHeaders(List.of(
                "Authorization",
                "Content-Type",
                "Accept",
                "X-Requested-With"
        ));

        // Espone l'header Authorization nelle risposte (necessario per JWT)
        config.setExposedHeaders(List.of("Authorization"));

        // Permette cookie/credenziali cross-origin
        config.setAllowCredentials(true);

        // Cache preflight per 1 ora (riduce le richieste OPTIONS)
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", config);

        return new CorsFilter(source);
    }
}
