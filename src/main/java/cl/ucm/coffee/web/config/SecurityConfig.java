package cl.ucm.coffee.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeHttpRequests()

                // Autenticación
                .requestMatchers("/api/auth/**").permitAll()

                // Permitir acceso a endpoints de café por parametros
                .requestMatchers(HttpMethod.GET, "/api/coffee/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/coffee/getCoffeeByName/{name}").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/coffee/getCoffeeById/{id}").permitAll()

                // Permitir acceso a endpoints de testimonios
                .requestMatchers(HttpMethod.POST, "/api/testimonials/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/testimonials/create").permitAll()

               // Permitir acceso a endpoints de cafes CRUD
                .requestMatchers(HttpMethod.GET, "/api/coffee/listCoffeesWithTestimonials").permitAll()
                //.requestMatchers(HttpMethod.POST, "/api/coffee/createCoffee").hasRole("ADMIN")

                .requestMatchers(HttpMethod.POST, "/api/coffee/createCoffee").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/coffee/updateCoffee/{id}").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/coffee/deleteCoffee/{id}").permitAll()


                //Endpoints para usuarios CRUD
                .requestMatchers(HttpMethod.GET, "/api/users/getAllUsers").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/users/getUserByName/{username}").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/users/create").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/users/update/{username}").permitAll()


                //falta restringir acceso.

                // Cualquier otra solicitud debe estar autenticada
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
