import helpers.*;
import models.AuthenticationResponseModel;
import models.ErrorModel;
import models.NewUserModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationTest2 implements TestHelper{



        @Test
        public void RegistrationTestPositive() throws IOException {
            NewUserModel newUserModel=new NewUserModel(EmailGenerator.generateEmail(3,2,2), PropertiesReader.getProperty("newUserPassword"));
            RequestBody requestBody= RequestBody.create(gson.toJson(newUserModel),JSON);
            Request request=new Request.Builder().url(REGISTRATION_PATH).post(requestBody).build();
            Response response=client.newCall(request).execute();
            String res=response.body().toString();
            System.out.println("Response "+res);
            if(response.isSuccessful()){
                AuthenticationResponseModel responseModel=gson.fromJson(res, AuthenticationResponseModel.class);
                System.out.println("Token"+responseModel.getToken());
                PropertiesWriterXML propertiesWriterXML=new PropertiesWriterXML(FILE_PATH);
                propertiesWriterXML.setProperties(TOKEN_KEY,responseModel.getToken(),false);
                Assert.assertTrue(response.isSuccessful() );

            }
            else{
                ErrorModel errorModel=gson.fromJson(response.body().string(), ErrorModel.class);
                System.out.println(errorModel.getStatus());
                System.out.println(errorModel.getError());
                System.out.println(errorModel.getMessage());
            }

        }
    }

