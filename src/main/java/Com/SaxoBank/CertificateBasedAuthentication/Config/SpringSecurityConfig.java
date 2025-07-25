package Com.SaxoBank.CertificateBasedAuthentication.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{	    
	    
	   
	    	
	    	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    	        auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
	    	    }
	    	  	
	    	
	    	  protected void configure(HttpSecurity http) throws Exception {
	    		  http.csrf().disable();
	    		  
	    	        http.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic();
	    	
	    	    }
	    	  
	    	    @Bean
	    	    public static NoOpPasswordEncoder passwordEncoder() {
	    	        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	    	    }
	    	

	    	    	
}
