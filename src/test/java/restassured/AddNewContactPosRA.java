package restassured;

import helpers.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.ContactModel;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddNewContactPosRA implements TestHelper {
    @Test
    public void addNewContactRA(){
        RestAssured.baseURI=ADD_CONTACT_PATH;
        ContactModel contactModel=new ContactModel(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(), EmailGenerator.generateEmail(2,3,2),
                PhoneNumberGenerator.generatePhoneNumber(), AddressGenerator.generateAddress(),"aa");
        //TODO Homework write id
       // ContactModel con=
        given().header(AuthorizationHeader,PropertiesReader.getProperty("token"))
                .body(contactModel).contentType(ContentType.JSON).when()
                .post().then().assertThat().statusCode(200);
    }
}
