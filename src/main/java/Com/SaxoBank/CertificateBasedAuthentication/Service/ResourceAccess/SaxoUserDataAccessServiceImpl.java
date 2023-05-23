package Com.SaxoBank.CertificateBasedAuthentication.Service.ResourceAccess;

import Com.SaxoBank.CertificateBasedAuthentication.CustomException.BusinessException;
import Com.SaxoBank.CertificateBasedAuthentication.CustomException.InternalException;
import Com.SaxoBank.CertificateBasedAuthentication.CREDENTIALS.Constants;
import Com.SaxoBank.CertificateBasedAuthentication.Service.Authorization.SaxoBankTokenService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SaxoUserDataAccessServiceImpl implements SaxoUserDataAccessService{

    @Autowired
    SaxoBankTokenService saxoBankTokenService;

    @Override
    public String getAllSaxoUserData(String endpointURL) throws Exception {
        String res = null;
        //	String apiurls=endpointURL.getEndpointurlSaxo();
        try {
            if(endpointURL==null) {
                throw new BusinessException(601,"Please insert endpoint URL");
            }
        } catch (BusinessException e) {
            // TODO: handle exception
            return "Error Code "+e.getErrorCode()+":"+e.getErrorMessage();
        }

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .header("Authorization","Bearer "+saxoBankTokenService.getAccessToken(Constants.tokenURL.getValue()))
                    .header("accept","application/json;odata=verbose")
                    .url(endpointURL)
                    .build();

            Response response = client.newCall(request).execute();

            res= response.peekBody(2048).string();


            try {
                if(response.code()>=400 && response.code()<500) {

                    throw new BusinessException(response.code(),"Bad Request,Please check the request parameters properly ");

                }

            }catch (BusinessException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
                return e.getErrorCode()+":"+e.getErrorMessage();
            }


            try {
                if(response.code()>=500) {

                    throw new InternalException(response.code(),"Internal Server Error occured ");

                }

            } catch (InternalException ex) {
                // TODO Auto-generated catch block
                return ex.getErrorCode()+":"+ex.getErrorMessage();
            }


        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;

    }

}
