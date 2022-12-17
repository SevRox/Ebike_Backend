package tech.getarays.backend.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@CrossOrigin(origins = "http://ebikewebsitehosting.s3-website.eu-central-1.amazonaws.com")
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/web/login")){ // if the user wants to login let him do it
            filterChain.doFilter(request, response); // pasing request to next filter
        } else {
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            log.info("Recived Header: {}", authorizationHeader);
            if(authorizationHeader != null && authorizationHeader.startsWith("iWantedToUseCurseWordButItsAProcjetForSomeOneToRead ")) {
                try {
                    String token = authorizationHeader.substring("iWantedToUseCurseWordButItsAProcjetForSomeOneToRead ".length());
                    Algorithm algorithm = Algorithm.HMAC256("changemeufokingidiot".getBytes());
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);
                    String username = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim("role").asArray(String.class);
                    Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority("User"));
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                } catch (Exception exception){
                    response.setHeader("Error", exception.getMessage());
                    //response.sendError(FORBIDDEN.value());
                    response.setStatus(FORBIDDEN.value());
                    Map<String, String> error = new HashMap<>(); //better to have class for errors building
                    error.put("ERROR_MSG:", exception.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);

                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
