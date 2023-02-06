package es.eoi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private UserDetailsService uds;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/welcome",true)
                .permitAll()
        );

        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
        );

        http.authorizeHttpRequests()
                .requestMatchers("/home","/registro","/guardar","/").permitAll()
                .requestMatchers("/admin").hasAuthority("Admin")
                .requestMatchers("/entr").hasAuthority("Entrenadores")
                .requestMatchers("/nutr").hasAuthority("Nutricionistas")
                .requestMatchers("/clie").hasAuthority("Clientes")
                .requestMatchers("/hr").hasAuthority("HR")
                .requestMatchers("/common").hasAnyAuthority("Entrenadores,Nutricionistas,Admin")
                .anyRequest().authenticated()

                .and()
                .exceptionHandling()
                .accessDeniedPage("/accessDenied")

                .and()
                .authenticationProvider(authenticationProvider());
        return http.build();

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(uds);
        authenticationProvider.setPasswordEncoder(encoder);
        return authenticationProvider;
    }

}
