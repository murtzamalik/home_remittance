package org.ais.hr.fileupload.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 11/1/2021
 * Time: 12:26 AM
 * Project : jscash
 */


public class JsHomeRemitFilter extends GenericFilterBean implements Filter {
    Logger LOG = LoggerFactory.getLogger(JsHomeRemitFilter.class);


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Methods", "*");
        httpResponse.setHeader("Access-Control-Allow-Headers", "*");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Max-Age", "3600");

        final String headerValue1 = httpRequest.getHeader("Authorization1");
      final String contextPath = httpRequest.getRequestURL().toString();
          if (contextPath.contains("js_hr/uploadfile") ) {
            final String headerValue = httpRequest.getHeader("Authorization");
            if (headerValue != null && headerValue.startsWith("Bearer")) {
                String jwt = headerValue.substring(7);
                JWTSecurity jwtSecurity = new JWTSecurity();
                HashMap<String, String> data = jwtSecurity.parseCLientSecretJWT(jwt);
                if (data != null) {
                    String id = data.get("ID");
                    String issue = data.get("Issuer");

                    if (id.equals("JS_CASH_MNG") && issue.equals("AiS")) {
                        LOG.info("\n INTERCEPTOR CLASS \n AGENT AUTHORIZED SUCCESSFULLY \n\n");
                        chain.doFilter(request, response);
                    } else {
                        LOG.info("\n INTERCEPTOR CLASS \n AGENT NOT AUTHORIZED THROUGH JWT \n\n");
                        throw new JsHomeRemitException("JWT AUTH AUTHORIZATION FAILED");
                    }

                } else {
                    LOG.info("\n INTERCEPTOR CLASS \n AGENT NOT AUTHORIZED THROUGH JWT \n\n");
                    throw new JsHomeRemitException("SESSION AUTHENTICATION FAILED");
                }

            } else {
                LOG.info("\n INTERCEPTOR CLASS \n AGENT NOT AUTHORIZED THROUGH JWT \n\n");
                throw new JsHomeRemitException("Invalid Authorization header value.");
            }
        } else if (contextPath.contains("jscash/nonFin") || contextPath.contains("jscash/fin")) {

            final String headerValue = httpRequest.getHeader("Authorization");
            if (headerValue != null && headerValue.startsWith("Bearer")) {
                String jwt = headerValue.substring(7);
                JWTSecurity jwtSecurity = new JWTSecurity();
                HashMap<String, String> data = jwtSecurity.parseJWT(jwt);
                if (data != null) {
                    if (data.get("Expired").contains("N")) {

                        String id = data.get("ID");
                        String issue = data.get("Issuer");

                        if (id.equals("JS_CASH_MNG") && issue.equals("AiS")) {
                            LOG.info("\n INTERCEPTOR CLASS \n AGENT AUTHORIZED SUCCESSFULLY \n\n");
                            chain.doFilter(request, response);
                        } else {
                            LOG.info("\n INTERCEPTOR CLASS \n AGENT NOT AUTHORIZED THROUGH JWT \n\n");
                            throw new JsHomeRemitException("JWT AUTH AUTHORIZATION FAILED");
                        }
                    } else if (data.get("Expired").contains("Y")) {
                        LOG.info("\n INTERCEPTOR CLASS \n AGENT JWT TOKEN EXPIRED \n\n");
                        throw new JsHomeRemitException("SESSION  EXPIRED");
                    }
                } else {
                    LOG.info("\n INTERCEPTOR CLASS \n AGENT NOT AUTHORIZED THROUGH JWT \n\n");
                    throw new JsHomeRemitException("SESSION AUTHENTICATION FAILED");
                }

            } else {
                LOG.info("\n INTERCEPTOR CLASS \n AGENT NOT AUTHORIZED THROUGH JWT \n\n");
                throw new JsHomeRemitException("Invalid Authorization header value.");
            }
        }else if (contextPath.contains("/jscash/swagger-ui.html")) {
            chain.doFilter(request, response);
        }
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
} // The End...