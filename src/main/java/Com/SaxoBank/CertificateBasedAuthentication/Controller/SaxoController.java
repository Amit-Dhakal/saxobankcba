package Com.SaxoBank.CertificateBasedAuthentication.Controller;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import Com.SaxoBank.CertificateBasedAuthentication.Service.ResourceAccess.SaxoUserDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import Com.SaxoBank.CertificateBasedAuthentication.CREDENTIALS.Constants;
import Com.SaxoBank.CertificateBasedAuthentication.Service.Authorization.SaxoBankTokenService;

@RestController
@RequestMapping("/api/v1/saxocba")
public class SaxoController {
	
@Autowired
SaxoBankTokenService tokenService;

@Autowired
SaxoUserDataAccessService saxoUserDataAccessService;

@RequestMapping(value = "/accesstoken", method = RequestMethod.GET, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> generateAccessToken() throws NoSuchAlgorithmException,CertificateException, KeyStoreException, IOException, Exception {
	try{
		return new ResponseEntity<>(tokenService.getAccessToken(Constants.tokenURL.getValue()),HttpStatus.OK);
	} catch (Exception e) {
		throw new RuntimeException(e);
	}
}

@RequestMapping(value = "/getusers", method = RequestMethod.GET, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> getUsers(@RequestParam String endpointurlSaxo) throws NoSuchAlgorithmException,CertificateException, KeyStoreException, IOException, Exception {
		try{
			String token=tokenService.getAccessToken(Constants.tokenURL.getValue());
           //String jsonArray=tokenService.getAllSaxoUserData(endpointURL);
			String jsonArray=saxoUserDataAccessService.getAllSaxoUserData(endpointurlSaxo);
			return new ResponseEntity<>(jsonArray,HttpStatus.OK);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
