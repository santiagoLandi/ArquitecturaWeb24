package org.tudai.gateway.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.tudai.gateway.security.AuthotityConstant;
import org.tudai.gateway.security.jwt.JwtFilter;
import org.tudai.gateway.security.jwt.TokenProvider;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final TokenProvider tokenProvider;

    public SecurityConfig( TokenProvider tokenProvider ) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain( final HttpSecurity http ) throws Exception {
        http
                .csrf( AbstractHttpConfigurer::disable );
        http
                .sessionManagement( s -> s.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) );
        http
                .securityMatcher("/**" )
                .authorizeHttpRequests( authz -> authz
                        .requestMatchers(HttpMethod.POST, "/authenticate").permitAll()
                        //RUTAS POR VERBOS GET
                        .requestMatchers(HttpMethod.GET,"/reports/scooter-usage").hasAuthority(AuthotityConstant._MANTENIMIENTO)
                        .requestMatchers(HttpMethod.GET,"/reports/nearby-scooters").hasAuthority(AuthotityConstant._USUARIO)
                        .requestMatchers(HttpMethod.POST,"/users/register").hasAuthority(AuthotityConstant._USUARIO)
                        .requestMatchers("/maintenances/**").hasAuthority(AuthotityConstant._MANTENIMIENTO)
                        .requestMatchers("/**").hasAuthority(AuthotityConstant._ADMIN)
                        .anyRequest().authenticated()
                )
                .httpBasic( Customizer.withDefaults() )
                .addFilterBefore( new JwtFilter( this.tokenProvider ), UsernamePasswordAuthenticationFilter.class );
        return http.build();
    }

}

