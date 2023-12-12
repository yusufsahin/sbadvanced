package tr.com.provera.pameraapi.util;
import org.springframework.util.AntPathMatcher;

public class TrailingSlashInsensitivePathMatcher extends AntPathMatcher {

    @Override
    public boolean match(String pattern, String path) {
        // Your custom logic here
        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        return super.match(pattern, path);
    }
}