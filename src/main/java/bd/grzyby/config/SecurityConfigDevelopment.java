package bd.grzyby.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@Profile("development")
//public class SecurityConfigDevelopment {
//    @Bean
//    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(authorize -> authorize
//                .anyRequest().anonymous()
//        );
//        return http.build();
//    }
//}
