package restassured;

import helpers.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.ContactModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class UpdateAndDeleteContactTest implements TestHelper {
    String id;
    ContactModel contactModel;
    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI=ADD_CONTACT_PATH;
         contactModel=new ContactModel(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(), EmailGenerator.generateEmail(2,3,2),
                PhoneNumberGenerator.generatePhoneNumber(), AddressGenerator.generateAddress(),"aa");
        String message=given().header(AuthorizationHeader,PropertiesReader.getProperty("token")).body(contactModel).contentType(ContentType.JSON)
                .when().post().then().assertThat().statusCode(200).extract().path("message");

             id=IdExtractor.extractId(message);

    }

    @Test
    public  void updateContact(){
        contactModel.setId(id);
        contactModel.setEmail(EmailGenerator.generateEmail(3,3,2));
        given().header(AuthorizationHeader,PropertiesReader.getProperty("token")).body(contactModel).contentType(ContentType.JSON)
                .when().put().then().assertThat().statusCode(200).assertThat().body("message",containsString("update"));
    }
@Test
    public void deleteContactById(){
        //contactModel.setId(id);
        given().header(AuthorizationHeader,PropertiesReader.getProperty("token")).when().delete(id).then()
                .assertThat().statusCode(200).assertThat().body("message",containsString("deleted"));

}

}
