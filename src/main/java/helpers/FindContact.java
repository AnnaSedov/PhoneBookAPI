package helpers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.ContactListModel;
import models.ContactModel;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class FindContact  {
   public static String findContact(String id,int num,String email) {
       RestAssured.baseURI = PropertiesReader.getProperty("getAllContacts");
       ContactListModel responseBody = given()
               .body(ContactListModel.class)
               .contentType(ContentType.JSON)
               .when()
               .header("Authorization", PropertiesReader.getProperty("token")).get(baseURI)
               .then().log().all().assertThat()
               .statusCode(200).extract().as(ContactListModel.class);

       for (ContactModel contactModel : responseBody.getContacts()) {

         if(contactModel.getId().equals(id)){
             if(num==0){
                return "contact with id: "+id+" exist";}
             if(!(num==1&&contactModel.getEmail().equals(email))){
                String s= "contact with id: "+id+"exist and was updated";
                return s;
             }
         }


       }
return "contact not exist";
   }
   }

