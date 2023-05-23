package Com.SaxoBank.CertificateBasedAuthentication.CREDENTIALS;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Certificate {
	private static String userid="17695456";
    private static String clientCertSerialNumber="6B00000DC6E45843E023524F14000200000DC6";
	private static String certThumbPrint ="d956b0a8c65f3c2b78faf064bf4234a85072d324";
	private X509Certificate x509Certificate;
	private PrivateKey privateKey;

//	public static String privateKeyFile = "C:\\Users\\Amit-PC\\Desktop\\saxokey\\private-key-with-cert.pem";
	//certificate path
	//String filename = "abc.access";
//	InputStream in = getClass().getClassLoader().getResourceAsStream(filename);


	public static String getUserid() {
		return userid;
	}

	public static void setUserid(String userid) {
		Certificate.userid = userid;
	}

	public static String getClientCertSerialNumber() {
		return clientCertSerialNumber;
	}

	public static void setClientCertSerialNumber(String clientCertSerialNumber) {
		Certificate.clientCertSerialNumber = clientCertSerialNumber;
	}

	public static String getCertThumbPrint() {
		return certThumbPrint;
	}

	public static void setCertThumbPrint(String certThumbPrint) {
		Certificate.certThumbPrint = certThumbPrint;
	}

	public X509Certificate getX509Certificate() {
		return x509Certificate;
	}

	public void setX509Certificate(X509Certificate x509Certificate) {
		this.x509Certificate = x509Certificate;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}
}
