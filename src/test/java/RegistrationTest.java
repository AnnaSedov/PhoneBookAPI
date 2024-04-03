import helpers.EmailGenerator;
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

public class RegistrationTest {
@Test
    public void RegistrationPositive() throws IOException {
    AuthenticationRequestModel requestModel = AuthenticationRequestModel.username(EmailGenerator.generateEmail(3,3,2))
            .password(PropertiesReader.getProperty("newUserPassword"));
    System.out.println("REQUEST: "+requestModel.getUsername()+" : "+requestModel.getPassword());
    //PropertiesWriter.writeProperties("emailNew",requestModel.getUsername(),false);
    PropertiesWriter.writeProperties("emailNew",requestModel.getUsername(),false);
    RequestBody requestBody = RequestBody.create(TestConfig.gson.toJson(requestModel),TestConfig.JSON);

    Request request = new Request.Builder().url(PropertiesReader.getProperty("BaseUrlRegistr")).post(requestBody).build();
    System.out.println("REQ "+request.toString());

    Response response = TestConfig.client.newCall(request).execute();
    System.out.println("Response code: "+response.code());

    if(response.isSuccessful()){


        AuthenticationResponseModel responseModel = TestConfig.gson.fromJson(response.body().string(),AuthenticationResponseModel.class);

        PropertiesWriter.writeProperties("tokenNew",responseModel.getToken(), false);

        Assert.assertTrue(response.isSuccessful());
    }
    else {
        System.out.println("Error");
    }



}
}
