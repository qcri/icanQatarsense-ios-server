package com.qsense.filters;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import com.qsense.service.UserService;
import com.qsense.util.ApplicationContextProvider;




public class MobileAuthFilter implements Filter,Serializable{
	
	/**
	 *
	 */
	private static final long serialVersionUID = -1638399513011989185L;
	
	
	private UserService userService;	
    
    @Override
    public void destroy() {
        // Do nothing
    }
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
    		FilterChain chain) throws IOException, ServletException,JSONException {
    	userService = ApplicationContextProvider.getApplicationContext().getBean(
    			"userService", UserService.class);
    	HttpServletRequest request = (HttpServletRequest) req;            

    	String authToken = request.getHeader("authToken");        
    	String userName = request.getHeader("userName");
    	String password = request.getHeader("password");
    	String userId = request.getHeader("userId");
    	try{
    		if (authToken != null) {

    			boolean result = userService.verifyAuthToken(authToken, userId);
    			if(result) {                    
    				chain.doFilter(req, res);                                          
    			}else {                    
    				((HttpServletResponse) res).sendError(401, "Authentcation Failed");
    			}

    		}else if(userName != null && password != null) { 
    			boolean result = userService.verifyLogin(userName, password);
    			try{
    				if(result) {                    
    					chain.doFilter(req, res);                                          
    				}else {                    
    					((HttpServletResponse) res).sendError(401, "Authentcation Failed");
    				}
    			}catch(Exception e){
    				((HttpServletResponse) res).sendError(401, "invalid login");
    			}                	

    		}else{
    			((HttpServletResponse) res).sendError(401, "Authentcation Failed");
    		}

    	} catch(Exception e){            
    		((HttpServletResponse) res).sendError(500, "Operation Failed");
    	}
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
		/*ServletContext servletContext = filterConfig.getServletContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		
		AutowireCapableBeanFactory autowireCapableBeanFactory = webApplicationContext.getAutowireCapableBeanFactory();
		
		autowireCapableBeanFactory.configureBean(this, "userService");*/
    }
}