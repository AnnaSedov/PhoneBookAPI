package restassured;

import helpers.PropertiesReader;
import helpers.TestConfig;
import helpers.TestHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class AllContactsTest implements TestHelper {
    @Test
    public void GetAllContactsTest2() throws IOException {
        //positive get all contacts
        RestAssured.baseURI = PropertiesReader.getProperty("getAllContacts");
// log file for analyse
    File logFile=new File("src/logs/testresult.log");
    if(!logFile.exists()){
        logFile.getParentFile().mkdirs();
        logFile.createNewFile();
    }
        PrintStream printStream=new PrintStream(new FileOutputStream(logFile));
    System.setOut(printStream);
    System.setErr(printStream);


        ContactListModel responseBody=given()
                .body(ContactListModel.class)
                .contentType(ContentType.JSON)
                .when()
                .header("Authorization",PropertiesReader.getProperty("token")).get(baseURI)
                .then().log().all().assertThat()
                .statusCode(200).extract().as(ContactListModel.class);

for(ContactModel contactModel:responseBody.getContacts()){
    System.out.println(contactModel.getId());
    System.out.println(contactModel.getName());
    System.out.println(contactModel.getLastName());
    System.out.println("*************");

}
printStream.close();


    }
@Test
    public void loginNegative(){
        AuthenticationRequestModel authenticationRequestModel=
                AuthenticationRequestModel.username(PropertiesReader.getProperty("existingUserEmail")).password("11111");
        ErrorModel errorModel=given().body(authenticationRequestModel)
                .contentType(ContentType.JSON)
                .when().post(LOGIN_PATH).then().log().all().assertThat()
                .statusCode(401).extract().as(ErrorModel.class);
        System.out.println(errorModel.getError()+":"+errorModel.getMessage());
}
}
