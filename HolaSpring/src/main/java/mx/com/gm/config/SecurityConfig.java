package mx.com.gm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;

//    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /*
    // * Se elimina el usuario por defualt para agregar los roles que pueden acceder a dichos recursos
    // ? Autenticación
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                    .password("{noop}123")  // * Con los corchetes le decimos que no queremos que aún se encripte la contraseña
                    .roles("ADMIN", "USER")
                .and()
                .withUser("user")
                    .password("{noop}123")
                    .roles("USER");
    }
*/
    // * Se coloca quienes pueden acceder a tales rutas con los roles
    // ? Autorización
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // * Solo los admin pueden entrar a estas rutas
                .antMatchers("/editar/**", "/agregar/**", "/eliminar")
                .hasRole("ADMIN")
                // * Cualquiera puede entrar a la raíz
                .antMatchers("/")
                .hasAnyRole("USER", "ADMIN")
                .and()
                // * En caso de no estar registrado que lo devuelva a la página de Login personalizada, gracias a que lo agregamos en WebConfig
                .formLogin()
                .loginPage("/login")
                .and()
                // * En caso de tener un 403, lo redirigue a una página personalizada
                .exceptionHandling()
                .accessDeniedPage("/errores/403");

    }

}
