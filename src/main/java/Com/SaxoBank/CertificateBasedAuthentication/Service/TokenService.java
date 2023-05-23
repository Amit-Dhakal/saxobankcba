package Com.SaxoBank.CertificateBasedAuthentication.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import Com.SaxoBank.CertificateBasedAuthentication.Model.SaxoEndpointURL;


public interface TokenService {
		
	public String getJWTToken(String certificateFilePath) throws FileNotFoundException,NoSuchAlgorithmException,CertificateException,IOException,KeyStoreException, Exception;
	public String getAccessToken(String tokenURL) throws NoSuchAlgorithmException, CertificateException, KeyStoreException, IOException, Exception;	
	public String getAllSaxoUserData(String endpointURL) throws Exception;

	
}
