package restassured;

import helpers.PropertiesReader;
import helpers.TestConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class AllContactsTest {
    @Test
    public void GetAllContactsTest2(){
        //positive get all contacts
        RestAssured.baseURI = PropertiesReader.getProperty("getAllContacts");


        ContactListModel responseBody=given().body(ContactListModel.class)
                .contentType(ContentType.JSON).when().header("Authorization",PropertiesReader.getProperty("token")).get(baseURI)
                .then().assertThat()
                .statusCode(200).extract().as(ContactListModel.class);

for(ContactModel contactModel:responseBody.getContacts()){
    System.out.println(contactModel.getId());
    System.out.println(contactModel.getName());
    System.out.println(contactModel.getLastName());
    System.out.println("*************");

}



    }
}
