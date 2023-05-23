package Com.SaxoBank.CertificateBasedAuthentication.Service.Authorization;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.assertj.core.util.Lists;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import com.google.gson.Gson;
import Com.SaxoBank.CertificateBasedAuthentication.CustomException.BusinessException;
import Com.SaxoBank.CertificateBasedAuthentication.CREDENTIALS.Certificate;
import Com.SaxoBank.CertificateBasedAuthentication.CREDENTIALS.Constants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.micrometer.core.instrument.util.IOUtils;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


@Service
public class SaxoTokenServiceImpl implements SaxoBankTokenService {

		
	@Override
	public String getJWTToken(String certificateFilePath) throws Exception {
		
		//RSAPrivateKey prvKey=readPrivateKey(new File(Certificate.privateKeyFile));
				
	    File file = ResourceUtils.getFile("classpath:private-key-with-cert.pem");
		RSAPrivateKey prvKey=readPrivateKey(file);
        long timestamp = Instant.now().getEpochSecond();
        Map<String,Object> claims = new HashMap<>();
        claims.put("iss",Constants.client_id);
         claims.put("sub",Constants.userid);
         claims.put("exp", timestamp + 3000000);
        claims.put("aud","https://sim.logonvalidation.net/token");
         claims.put("spurl","koizai-qa_C76B25B9-5D99-451F-8144-9D45690DEF8D");
  String token=Jwts.builder().setHeaderParam("alg","RS256").setHeaderParam("x5t",Certificate.getCertThumbPrint()).setHeaderParam("typ","JWT").setClaims(claims).signWith(SignatureAlgorithm.RS256,prvKey).compact();
		return token.toString();   
	}


	@Override
	public String getAccessToken(String tokenURL) throws Exception {
		// TODO Auto-generated method stub	     		
		try {		
			if(tokenURL==null) {				
				 throw new BusinessException(601,"Please insert token endpoint URL");
			}
			
		} catch (BusinessException e) {
			// TODO: handle exception
			
			return "Error Code:"+e.getErrorCode()+"Error Message :"+e.getErrorMessage();
		}
				
	     String encodedCredentials=getBase64Encoded(Constants.client_id.getValue(), Constants.client_secrets.getValue());
	 
	       CloseableHttpClient httpClient = HttpClients.createDefault();
	       
	        HttpPost postRequest = new HttpPost(tokenURL);
	        
	        postRequest.setHeader("Content-Type", "application/x-www-form-urlencoded");
	        postRequest.setHeader("Authorization","Basic "+encodedCredentials);

	        List<NameValuePair> params = Lists.newArrayList(
	                new BasicNameValuePair("grant_type", "urn:saxobank:oauth:grant-type:personal-jwt"),
	                new BasicNameValuePair("assertion",getJWTToken(Constants.certificatePath.getValue()))
	        );        
	        try {
	            postRequest.setEntity(new UrlEncodedFormEntity(params));
	            
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
           String bearerToken = "";

	        try {

	            HttpResponse response = httpClient.execute(postRequest);       
	            System.out.println("Response is ::"+response);
	            
	            String bodyJson =IOUtils.toString(response.getEntity().getContent(),Charset.defaultCharset());  
	            System.out.println("JSON BODY ::"+bodyJson);
	                      	             	       	            
	            if (bodyJson.contains("access_token")) {
	                int i1 = bodyJson.indexOf("access_token");	                
	                int i3 = bodyJson.indexOf("token_type");	     

	                System.out.println("index is ::"+i1);
	                
	                String str1 = bodyJson.substring(i1+15,i3-i1-1);
	                                
	                bearerToken = str1;
	            }	                             

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
			return bearerToken;
		
			}
	
	
	
	
	//This method is not working
	public String generateAccessToken(String tokenURL) throws Exception {
	    String encodedCredentials=getBase64Encoded(Constants.client_id.getValue(), Constants.client_secrets.getValue());

	 	//	RequestBody body = RequestBody.create(null, new byte[]{});     
		OkHttpClient client = new OkHttpClient();
		
		FormBody formBody = new FormBody.Builder()
				.add("grant_type","urn:saxobank:oauth:grant-type:personal-jwt")		
				.add("assertion",getJWTToken(Constants.certificatePath.getValue()))
				.build(); 
		
		
		Headers headers = new Headers.Builder()
		       .add("Authorization", "Basic " +encodedCredentials)
		        .add("Content-Type", "application/x-www-form-urlencoded")
		        .add("Accept", "application/json")
		        .build();
	
	    Request request = new Request.Builder()
	    		.url(tokenURL)    		
	    		.post(formBody)
	  		    .headers(headers)
	  		    .build(); 
	    
	    Gson gson = new Gson();
	    String jsonRequest = gson.toJson(request);

	    
	   
		Response response = client.newCall(request).execute();
	    	
		return response.peekBody(2048).string();
	
	}
	
	
	
	public RSAPrivateKey readPrivateKey(File file) throws Exception {
	    KeyFactory factory = KeyFactory.getInstance("RSA");

	    try (FileReader keyReader = new FileReader(file);
	      PemReader pemReader = new PemReader(keyReader)) {

	        PemObject pemObject = pemReader.readPemObject();
	        byte[] content = pemObject.getContent();  	        
	        PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(content);        
	        return (RSAPrivateKey) factory.generatePrivate(privKeySpec);
	    }
	}	
	
		public static String getBase64Encoded(String id,String password) {					
			return Base64.getEncoder().encodeToString((id+":"+password).getBytes(StandardCharsets.UTF_8));
					
			}
		
		
		


		//exception Handling all parts 
		//Spring security

//	public static void main(String[] args) throws Exception {
//		TokenServiceImpl tokenServiceImpl =new TokenServiceImpl();
//		//System.out.println(tokenServiceImpl.readPrivateKey(new File(Certificate.privateKeyFile)));
//		System.out.println("GENERATED TOKEN"+tokenServiceImpl.generateAccessToken(Constants.tokenURL));
//
//	}

}
