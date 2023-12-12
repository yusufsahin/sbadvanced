package tr.com.provera.pameraapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import tr.com.provera.pameraapi.dao.RoleRepository;
import tr.com.provera.pameraapi.dao.model.Privilege;
import tr.com.provera.pameraapi.dao.model.Role;
import tr.com.provera.pameraapi.dao.model.User;
import tr.com.provera.pameraapi.util.Constant;
import tr.com.provera.pameraapi.util.JWTAuthority;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class TokenProvider implements Serializable {
    private final SecretKey key = Keys.hmacShaKeyFor(Constant.SIGNING_KEY.getBytes());

    @Autowired
    private RoleRepository roleRepository;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build().parseSignedClaims(token).getPayload();
    }

    private Boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    public String generateToken(Authentication authentication) {
        final List<Role> roles = authentication.getAuthorities().stream()
                .filter(authority -> authority.getAuthority().startsWith("ROLE_"))
                .map(authority -> roleRepository.findByName(authority.getAuthority()))
                .toList();

        final List<String> scopes = roles.stream().map(Role::getName).collect(Collectors.toList());

        final List<JWTAuthority> authorities = roles.stream().map(role -> new JWTAuthority(role.getName(),
                        role.getPrivileges().stream().map(Privilege::getName).collect(Collectors.toList())))
                .collect(Collectors.toList());

        return Jwts.builder().subject(authentication.getName())
                .claim(Constant.AUTHORITIES_KEY, scopes)
                .claim("authorities", authorities)
                .header().add("typ", "JWT").and()
                .signWith(key).issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + Constant.ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
                .compact();
    }

    public String generateTokenByUser(User user) {
        Set<Role> uniqueRoles = new HashSet<>(user.getRoles());

        List<String> scopes = uniqueRoles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        List<JWTAuthority> authorities = uniqueRoles.stream()
                .map(role -> new JWTAuthority(role.getName(),
                        role.getPrivileges().stream().map(Privilege::getName).collect(Collectors.toList())))
                .collect(Collectors.toList());

        return Jwts.builder().subject(user.getUsername())
                .claim(Constant.AUTHORITIES_KEY, scopes)
                .claim("authorities", authorities)
                .header().add("typ", "JWT").and()
                .signWith(key).issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + Constant.ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        return getUsernameFromToken(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth, final UserDetails userDetails) {
        final Claims claims = getAllClaimsFromToken(token);

        final Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        extractAuthoritiesFromClaims(claims, Constant.AUTHORITIES_KEY, authorities);
        extractCustomAuthorities(claims, "authorities", authorities);

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    private void extractAuthoritiesFromClaims(Claims claims, String key, Collection<SimpleGrantedAuthority> authorities) {
        List<String> roles = claims.get(key, List.class);
        if (roles != null) {
            roles.stream()
                    .filter(Objects::nonNull)
                    .map(String::valueOf)
                    .map(SimpleGrantedAuthority::new)
                    .forEach(authorities::add);
        }
    }

    private void extractCustomAuthorities(Claims claims, String key, Collection<SimpleGrantedAuthority> authorities) {
        List<LinkedHashMap<String, Object>> customAuthorities = claims.get(key, List.class);
        if (customAuthorities != null) {
            customAuthorities.stream()
                    .filter(Objects::nonNull)
                    .flatMap(authority -> {
                        return ((List<String>) authority.get("privileges")).stream();
                    })
                    .filter(Objects::nonNull)
                    .map(String::valueOf)
                    .distinct()
                    .map(SimpleGrantedAuthority::new)
                    .forEach(authorities::add);
        }
    }
}
