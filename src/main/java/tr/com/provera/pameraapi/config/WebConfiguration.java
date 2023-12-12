package tr.com.provera.pameraapi.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tr.com.provera.pameraapi.util.TrailingSlashInsensitivePathMatcher;

@Configuration

public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setPathMatcher(new TrailingSlashInsensitivePathMatcher());

    }

}

