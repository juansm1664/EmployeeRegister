package col.com.jager.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {

            //Los Bean Son Objetos que maneja el contenedor Spring, su fin es el de
    @Bean   // Suministrar objetos a una clasa sin que la propia clase tenga que crearlos.
    public LocaleResolver localeResolver(){
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("en"));
        return slr;

    }
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        var lci =new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registro){

        registro.addInterceptor(localeChangeInterceptor());
    }
    @Override //Sobre Escribir metodos
    public void addViewControllers(ViewControllerRegistry registro ) {

        registro.addViewController("/").setViewName("index"); //Mapear path que no pasan por el controlador
        registro.addViewController("/login");
        registro.addViewController("/errores/403").setViewName("/errores/403");
    }

}
