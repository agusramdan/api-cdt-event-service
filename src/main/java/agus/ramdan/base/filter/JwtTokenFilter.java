package agus.ramdan.base.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.val;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
@Profile("!oauth2")
public class JwtTokenFilter extends OncePerRequestFilter {

    /**
     * Fungsi untuk mendecode token JWT tanpa verifikasi signature,
     * serta memparsing header dan payload ke dalam Map.
     *
     * @param jwt token JWT dalam format header.payload.signature
     * @return Map dengan kunci "header" dan "payload" yang masing-masing berisi Map dari hasil parsing JSON.
     * @throws IllegalArgumentException jika token JWT tidak valid atau parsing JSON gagal.
     */
//    public static Map<String, Map<String, Object>> decodeJwt(String jwt) {
//        if (jwt == null || jwt.isEmpty()) {
//            throw new IllegalArgumentException("JWT token kosong atau null.");
//        }
//
//        // Token JWT biasanya memiliki tiga bagian: header.payload.signature
//        String[] parts = jwt.split("\\.");
//        if (parts.length < 2) {
//            throw new IllegalArgumentException("JWT tidak valid. Pastikan token memiliki format header.payload.signature.");
//        }
//
//        try {
//            // Decode bagian header dan payload menggunakan Base64 URL-safe decoder
//            String headerJson = new String(Base64.getUrlDecoder().decode(parts[0]), StandardCharsets.UTF_8);
//            String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);
//
//            // Parsing JSON ke dalam Map menggunakan Jackson
//            ObjectMapper objectMapper = new ObjectMapper();
//            Map<String, Object> headerMap = objectMapper.readValue(headerJson, new TypeReference<Map<String, Object>>() {});
//            Map<String, Object> payloadMap = objectMapper.readValue(payloadJson, new TypeReference<Map<String, Object>>() {});
//
//            Map<String, Map<String, Object>> result = new HashMap<>();
//            result.put("header", headerMap);
//            result.put("payload", payloadMap);
//            return result;
//        } catch (IllegalArgumentException | JsonProcessingException e) {
//            throw new IllegalArgumentException("Gagal mendecode atau parsing JSON token JWT.", e);
//        }
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        val authentication_old = SecurityContextHolder.getContext().getAuthentication();
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            Claims claims = Jwts.parserBuilder()
                    .build() // Tidak menggunakan setSigningKey
                    .parseClaimsJwt(token) // Perhatikan: `parseClaimsJwt` bukan `parseClaimsJws`
                    .getBody();
//            val map = decodeJwt(token);
//            val claims = new DefaultClaims(map.get("payload"));
            String userId = claims.getSubject();
            // Set Authentication ke SecurityContext
            Object object = claims.get("Role");
            val authentication = new UsernamePasswordAuthenticationToken(userId, token, new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(UsernamePasswordAuthenticationToken.authenticated(userId,token,new ArrayList<>()));
        }
        filterChain.doFilter(request, response);
        SecurityContextHolder.getContext().setAuthentication(authentication_old);
    }
}
