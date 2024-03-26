import helpers.PropertiesReader;
import helpers.TestConfig;
import models.AuthenticationRequestModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {
    @Test
    public void loginPositive() throws IOException {
        AuthenticationRequestModel requestModel=AuthenticationRequestModel.userName(PropertiesReader.getProperty("existingUserEmail")).password(PropertiesReader.getProperty("existingUserPassword"));
      
        RequestBody requestBody=RequestBody.create(TestConfig.gson.toJson(requestModel),TestConfig.JSON);
        Request request=new Request.Builder().url(PropertiesReader.getProperty("URL"))
                .post(requestBody).build();


        Response response=TestConfig.client.newCall(request).execute();
        if(response.isSuccessful()){
            AuthenticationRequestModel responseModel=TestConfig.gson.fromJson(response.body().string(),AuthenticationRequestModel.class);

            System.out.println("good");

        }
        else{
            System.out.println("Error");
        }

    }
}
