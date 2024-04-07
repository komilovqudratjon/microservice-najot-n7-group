package uz.najot.confing;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.najot.entity.Users;
import uz.najot.service.UserDB;

import java.io.IOException;
import java.util.Optional;

/**
 * @description: TODO
 * @date: 25 March 2024 $
 * @time: 6:19 PM 35 $
 * @author: Qudratjon Komilov
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDB userDB;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("i am working");

        // log request
//        request.getHeaderNames().asIterator().forEachRemaining(headerName -> {
//            log.info("Header name: {}, value: {}", headerName, request.getHeader(headerName));
//        });

        // step 1 get token from request
        Optional<String> token = getTokenFromRequest(request);
        if (token.isPresent()) {
            DecodedJWT decode = jwtService.decode(token.get());
//            Long id = decode.getClaim("id").asLong();
            Users userById = userDB.getUserById(Long.valueOf(decode.getSubject()));
            // step 3 get principalUser from decoded token
            userById.getRole().forEach(roles -> log.info("Role: {}", roles.getRole()));

            PrincipleUser user = PrincipleUser.builder()
                    .id(Long.valueOf(decode.getSubject()))
                    .username(userById.getUsername())
                    .password(userById.getPassword())
                    .roles(userById.getRole())
                    .active(userById.isActive())
                    .build();

            // step 4 check user
            SecurityContextHolder.getContext().setAuthentication(new UserTokenAuth(user));
        }
        filterChain.doFilter(request, response);


    }

    public Optional<String> getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if (bearer != null && bearer.startsWith("Bearer ")) {
            return Optional.of(bearer.substring(7));
        }
        return Optional.empty();
    }

}
