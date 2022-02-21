package br.com.allangf.ToDoWithUsersAPI.rest.config.jwt;


import br.com.allangf.ToDoWithUsersAPI.rest.service.impl.UserDetailsServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private UserDetailsServiceImpl userDetailsService;

    public JwtAuthFilter(JwtService jwtService, UserDetailsServiceImpl userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        if (authorization != null) {

            boolean isValid;
            String token;

            if (!authorization.startsWith("Bearer")) {
                authorization = "Bearer " + authorization;
            }
            token = authorization.split(" ")[1];
            isValid = jwtService.tokenValid(token);


            if (isValid) {
                String loginUser = jwtService.getLoginUser(token);
                UserDetails user = userDetailsService.loadUserByUsername(loginUser);
                UsernamePasswordAuthenticationToken userAuthenticated = new
                        UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                userAuthenticated.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(userAuthenticated);
            }
        }

        filterChain.doFilter(request, response);

    }
}
