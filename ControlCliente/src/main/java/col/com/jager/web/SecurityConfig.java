package col.com.jager.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

    @Configuration
    @EnableWebSecurity
    public class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Override //sobreEscribir Metodos
        protected void configure(AuthenticationManagerBuilder auth) throws Exception{
            auth.inMemoryAuthentication()  //Metodo para Restringir los usuarios que se van a poder ingresar
                    .withUser("admin")  //Autenticación: Concepto de Agregar nuevos usuarios
                        .password("{noop}123")
                        .roles("ADMIN","USER")
                    .and()
                        .withUser("juan")
                        .password("{noop}123")
                    .roles("USER")
                    ;
        }
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests() //Metodo para la Autorización de URL, permite visualizar o ejecutar una acción
                    .antMatchers("/editar/**", "/agregar/**","/eliminar") //Autorización: Es un Concepto el cual el Metodo para indicar los path que se van a usar
                        .hasAnyRole("ADMIN")
                    .antMatchers("/")
                        .hasAnyRole("USER", "ADMIN")
                    .and()
                        .formLogin()
                        .loginPage("/login")
                    .and()
                        .exceptionHandling().accessDeniedPage("/errores/403")
                    ;
        }
    }
