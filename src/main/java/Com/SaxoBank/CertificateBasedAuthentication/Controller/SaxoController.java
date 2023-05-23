package Com.SaxoBank.CertificateBasedAuthentication.Controller;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import Com.SaxoBank.CertificateBasedAuthentication.Model.Constants;
import Com.SaxoBank.CertificateBasedAuthentication.Model.SaxoEndpointURL;
import Com.SaxoBank.CertificateBasedAuthentication.Service.TokenService;

@RestController
@RequestMapping("api/v1/saxocba")
public class SaxoController {
	
@Autowired 
TokenService tokenService;

@RequestMapping(value = "/accesstoken", method = RequestMethod.POST, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> getToken() throws NoSuchAlgorithmException,CertificateException, KeyStoreException, IOException, Exception {
	
	String token=tokenService.getAccessToken(Constants.tokenURL);
	return new ResponseEntity<>("Access Token:-"+token,HttpStatus.OK);	
	
}
	
	
	
@RequestMapping(value = "/getusers", method = RequestMethod.GET, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> getUsers(@RequestParam String endpointurlSaxo) throws NoSuchAlgorithmException,CertificateException, KeyStoreException, IOException, Exception {
		
	String token=tokenService.getAccessToken(Constants.tokenURL);
//String jsonArray=tokenService.getAllSaxoUserData(endpointURL);
	
	String jsonArray=tokenService.getAllSaxoUserData(endpointurlSaxo);

	return new ResponseEntity<>(jsonArray,HttpStatus.OK);	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
