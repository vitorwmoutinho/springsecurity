package dio.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private SecurityDatabaseService securityDatabaseService;
    @Autowired
    public void globalUserDetail(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(securityDatabaseService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin(Customizer.withDefaults())
                .authorizeHttpRequests((authz) ->authz.requestMatchers("/")
                        .permitAll().requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers("/users").hasAnyRole("USERS","MANAGERS")
                        .requestMatchers("/managers").hasAnyRole("MANAGERS")
                        .anyRequest().authenticated()
                ).httpBasic(Customizer.withDefaults());
        return http.build();

    }
    /*@Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.builder()
                .username("admin")
                .password("{noop}master123")
                .roles("MANAGERS") // Role 'MANAGERS'
                .build();
        UserDetails user2 = User.builder()
                .username("user")
                .password("{noop}user123")
                .roles("USERS")
                .build();

        return new InMemoryUserDetailsManager(user, user2);
    }
*/
}