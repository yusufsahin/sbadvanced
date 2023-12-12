package tr.com.provera.pameraapi.audit;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || isAnonymousAuthentication(authentication)) {
            return Optional.of("anonymousUser");
        }

        return Optional.ofNullable(authentication.getName());
    }

    private boolean isAnonymousAuthentication(Authentication authentication) {
        return "anonymousUser".equals(authentication.getPrincipal());
    }
}