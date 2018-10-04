package com.scc.judge.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.scc.judge.config.AuthenticateConfig;
import com.scc.judge.exceptions.ApiError;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class UserContextFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(UserContextFilter.class);

    @Autowired
    AuthenticateConfig authenticate;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {


        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        UserContextHolder.getContext().setAuthentificationKey( httpServletRequest.getHeader(UserContext.AUTHENTICATION_KEY) );

    	// Swagger Authentification disabled
    	if (httpServletRequest.getRequestURL().toString().indexOf("api-docs")>0
       		 || httpServletRequest.getRequestURL().toString().indexOf("swagger")>0) {
    		filterChain.doFilter(httpServletRequest, servletResponse);
    	} else {
	        logger.debug("Incoming Authentification key: {}", UserContextHolder.getContext().getAuthentificationKey());
	        String authCredentials = UserContextHolder.getContext().getAuthentificationKey();
	
	        if (authenticate(authCredentials)) {
	        	filterChain.doFilter(httpServletRequest, servletResponse);
	        } else {
				if (servletResponse instanceof HttpServletResponse) {
					HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

					httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					httpServletResponse.setContentType("application/json");

					httpServletResponse.getWriter().write(convertObjectToJson(new ApiError(HttpStatus.UNAUTHORIZED, "Authentification key is required")));
					
					logger.error("Erreur d'authentification, clef fournie: {}", authCredentials);
				}
			}
    	}                
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
    
    private boolean authenticate(String authCredentials) {
		Boolean ok = false;

		if (null == authCredentials)
			return ok;

		// la clé transmise est-elle reconnue ?
		for (String _key : authenticate.getKeys()) {
			if (_key.equals(authCredentials))
				ok = true;
		}

		if(!ok) {
			return false;
		}
		
		ok = false;

		Date today = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		// la clé est-elle toujours active ?
		String dateLimiteString = authenticate.getValue();
		if (dateLimiteString != null) {
			Date dateLimite = null;
			try {
				dateLimite = formatter.parse(dateLimiteString);

				if (dateLimite.after(today)) {
					ok = true;
				}
			} catch (ParseException e) {
				logger.error("Le format de la date associé à l'identifiant {} n'est pas au format valide (dd/MM/aaaa)",authCredentials);
			}
		}

		return ok;

	}        
    
    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        // jackson mapper @jsonformat not working
        mapper.findAndRegisterModules();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        //DateFormat df = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        //df.setLenient(false);
        //mapper.setDateFormat(df);

        return mapper.writeValueAsString(object);
    }
    
}