package Com.SaxoBank.CertificateBasedAuthentication.Service.Authorization;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;


public interface SaxoBankTokenService {
		
	public String getJWTToken(String certificateFilePath) throws FileNotFoundException,NoSuchAlgorithmException,CertificateException,IOException,KeyStoreException, Exception;
	public String getAccessToken(String tokenURL) throws NoSuchAlgorithmException, CertificateException, KeyStoreException, IOException, Exception;	
}
