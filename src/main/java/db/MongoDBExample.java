package db;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import helpers.*;
import models.ContactModel;
import org.bson.Document;

import static helpers.MongoDBConfig.*;


public class MongoDBExample {
    public static void main(String[] args) {
        addNewEntity();
    }
    public static void  addNewEntity(){
      //  MongoClient mongoClient=MongoClients.create("mongodb://localhost:27017");
        MongoClient mongoClient=MongoClients.create(CONNECT);
        MongoDatabase database=mongoClient.getDatabase(BASE);//test
        MongoCollection<Document> collection=database.getCollection(COLLECTION);//mycollection

        Document document=new Document("name","Alise").append("Age",25).append("email",EmailGenerator.generateEmail(2,3,2));
        collection.insertOne(document);
        ContactModel contactModel=new ContactModel(NameAndLastNameGenerator.generateName(),NameAndLastNameGenerator.generateLastName(),EmailGenerator.generateEmail(2,3,2), PhoneNumberGenerator.generatePhoneNumber(), AddressGenerator.generateAddress(),"");
        System.out.println(contactModel.toString());
        Document document1=new Document("contact",contactModel.toString());

        collection.insertOne(document1);


    }
}
