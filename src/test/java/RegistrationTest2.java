import helpers.*;
import models.AuthenticationResponseModel;
import models.ContactModel;
import models.ContactResponseModel;
import models.ErrorModel;
import models.NewUserModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationTest2 implements TestHelper  {



        @Test
        public void RegistrationTestPositive() throws IOException {
            NewUserModel newUserModel=new NewUserModel(EmailGenerator.generateEmail(3,2,2), PropertiesReader.getProperty("newUserPassword"));
            RequestBody requestBody= RequestBody.create(gson.toJson(newUserModel),JSON);
            Request request=new Request.Builder().url(REGISTRATION_PATH).post(requestBody).build();
            Response response=client.newCall(request).execute();
            String res=response.body().toString();
            System.out.println("Response "+res+" "+response.code());
            if(response.isSuccessful()){
             //   AuthenticationResponseModel responseModel= gson.fromJson(res,AuthenticationResponseModel.class); //!!!!
AuthenticationResponseModel responseModel = TestConfig.gson.fromJson(response.body().string(),AuthenticationResponseModel.class);
          //     AuthenticationResponseModel responseModel=gson.fromJson(res,AuthenticationResponseModel.class);
                String resultToken = responseModel.getToken();
                System.out.println("Token"+responseModel.getToken());
                PropertiesWriterXML propertiesWriterXML=new PropertiesWriterXML(FILE_PATH);
                propertiesWriterXML.setProperties(TOKEN_KEY,resultToken,false);
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

