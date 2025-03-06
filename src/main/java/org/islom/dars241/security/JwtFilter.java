package org.islom.dars241.security;

import org.islom.dars241.service.MyAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    MyAuthService authService;

    /// Bu Methodning vazifasi headerda bo'lgan tokenni olib sistemaga kiritadi va userni verify qiladi
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        //REQUESTDAN TOKEN OLISH
        String token = request.getHeader("Authorization");

        //TOKEN BORLIGINI VA TOKENNING BOSHLANISHI BEARER BO'LISHINI TEKSHIRYAPMIZ
        if (token != null && token.startsWith("Bearer")) {
            //BEARER TASHLAB QOLGAN QISMINI OLDIK
            token = token.substring(7);
            //TOKENNI VALIDATSIYADAN O'TKAZDIK(TOKEN BUZILMAGANINI VA MUDDATI O'TMAGANLIGINI VA HAKAZOLARNI TEKSHIRADI)
            boolean validateToken = jwtProvider.validateToken(token);
            if (validateToken) {
//TOKENNI ICHIDAN USERNAMENI OLDIK
                String username = jwtProvider.getUserNameFromToken(token);
                if (username != null) {

                    //USERNAME ORQALI USERDETAILSNI OLDIK
                    UserDetails userDetails = authService.loadUserByUsername(username);
                    //USERDETAIL ORQALI AUTHENTIFICATION YARATIB OLDIK
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails,
                                    null,
                                    userDetails.getAuthorities());

//              Bu odamnmi ssitemaga kirishga ruhsat ber
                    //SISTEMAGA KIM KIRGANILIGINI O'RNATDIK
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    System.out.println(SecurityContextHolder.getContext().getAuthentication());


                }
            }

        }
        //
        filterChain.doFilter(request, response);
    }
}
