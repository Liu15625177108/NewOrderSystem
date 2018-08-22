package hsbc.groupthree.ordersystem.commons.security;

import hsbc.groupthree.ordersystem.commons.utils.JwtTool;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * @author : Jeff.Li
 * @Package: hsbc.groupthree.ordersystem.commons.security
 * @Program: NewOrderSystem
 * @Description: todo
 * @date : 2018年08月21日 16:15:23
 **/
public class JwtAthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTool jwtTool;

    @Autowired
    private JwtUserInfoServicesImpl jwtUserInfoServices;

    @Autowired
    private JwtManagerServiceImpl jwtManagerService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        /** to get the token*/
        final String bearer = "Bearer ";
        String authheader = request.getHeader("Authorization");
        if (authheader != null && authheader.startsWith(bearer)) {

            /** to get the username in the body of token*/
            final String authToken = authheader.replace("Bearer ", "");
            String username = jwtTool.getUsernameFromToken(authToken);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails=null;
                if(request.getRequestURL().toString().contains("manager")) {
                     userDetails= jwtManagerService.loadUserByUsername(username);
                } else {
                    userDetails = jwtUserInfoServices.loadUserByUsername(username);
                }
                if (jwtTool.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    /** to set the spring-security authentication to user*/
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    /** to set userId in request, which will be used in controller*/
                    request.setAttribute("userId", jwtTool.getUserIdFromToken(authToken));
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
