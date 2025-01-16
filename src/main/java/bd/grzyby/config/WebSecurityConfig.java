package bd.grzyby.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
@Profile("security")
public class WebSecurityConfig {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/","/signin","/aboutproject","/access-denied","/authors").permitAll()
                        .requestMatchers("/partie","/zlecenie","/gatunki"
                        ,"/partie/add","/partie/addPartia","/partie/transfer","/partie/transfer/").hasRole("PRACOWNIK")
                        .requestMatchers("/zlecenie/*","/klienci","/klienci/*"
                                ,"/partie/*","/zlecenie/edit/*/*/remove","/zlecenie/edit/*/*","/zlecenie/delete/").hasRole("KIEROWNIK")
                        .requestMatchers("/gatunki/*","/gatunki/add","/gatunki/delete/","gatunki/edit/*"
                                ,"/pracownicy","/pracownicy/*","pracownicy/delete/","/pracownicy/edit/").hasRole("MANAGER")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/signin")
                        .loginProcessingUrl("/authenticate")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll)
                .exceptionHandling(configurer -> configurer
                        .accessDeniedPage("/access_denied")
                );

        return http.build();
    }
}
