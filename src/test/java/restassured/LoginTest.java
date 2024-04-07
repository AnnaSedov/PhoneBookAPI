package restassured;

import helpers.EmailGenerator;
import helpers.PasswordStringGenerator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.AuthenticationRequestModel;
import models.AuthenticationResponseModel;
import models.NewUserModel;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginTest {

   @Test
   public void registrationTestRestAssured(){
       String baseURI = "https://contactapp-telran-backend.herokuapp.com/v1/user/registration/usernamepassword";
       NewUserModel newUserModel=new NewUserModel("homeann71@gmail.com", "21212zZ!");
       String  token=given().body(newUserModel)
               .contentType(ContentType.JSON)
               .when().post(baseURI).then()
               .assertThat().statusCode(200).extract()
               .path("token");

System.out.println("Token "+token);
   }

    @Test
    public void loginTestRestAssured(){

        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword";
        AuthenticationRequestModel authenticationRequestModel=AuthenticationRequestModel.username("homeann7@gmail.com").password("21212zZ!");
        AuthenticationResponseModel responseModel=given().body(authenticationRequestModel).contentType(ContentType.JSON).when().post().then().log().all().assertThat().statusCode(200).extract().as(AuthenticationResponseModel.class);
        System.out.println("Token "+responseModel.getToken());

    }

}
