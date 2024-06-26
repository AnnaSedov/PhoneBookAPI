import helpers.PropertiesReader;
import helpers.PropertiesWriter;
import helpers.TestConfig;
import models.AuthenticationRequestModel;
import models.AuthenticationResponseModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {
    @Test
    public void loginPositive() throws IOException {

        AuthenticationRequestModel requestModel = AuthenticationRequestModel
                .username(PropertiesReader.getProperty("existingUserEmail"))
                .password(PropertiesReader.getProperty("existingUserPassword"));
        System.out.println("REQUEST: "+requestModel.getUsername()+" : "+requestModel.getPassword());

        RequestBody requestBody = RequestBody
                .create(TestConfig.gson.toJson(requestModel),
                        TestConfig.JSON);

        Request request = new Request.Builder()
                .url(PropertiesReader.getProperty("BaseUrlLogin"))
                .post(requestBody)
                .build();
        System.out.println("REQ "+request.toString());

        Response response = TestConfig.client.newCall(request).execute();
        System.out.println("Response code: "+response.code());

        if(response.isSuccessful()){


            AuthenticationResponseModel responseModel =
                    TestConfig.gson.fromJson(response.body().string(),
                            AuthenticationResponseModel.class);


            PropertiesWriter.writeProperties("token",responseModel.getToken(), false);
            Assert.assertTrue(response.isSuccessful());
        }
        else {
            System.out.println("Error");
        }


    }


    }

//registration hw

