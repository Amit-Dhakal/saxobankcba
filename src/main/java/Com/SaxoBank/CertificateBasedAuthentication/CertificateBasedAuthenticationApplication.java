package Com.SaxoBank.CertificateBasedAuthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;



@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Certificate Based Authentication Of Saxo Bank API", version = "1.0", description = "Saxo BANK Information"))
public class CertificateBasedAuthenticationApplication {
public static void main(String[] args) {
		SpringApplication.run(CertificateBasedAuthenticationApplication.class, args);
	
	}
	
	}
