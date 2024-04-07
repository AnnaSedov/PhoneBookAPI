package helpers;

import models.ContactModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseReader {
    static String  url="jdbc:mysql://localhost:3306/phonebook";
    static String username="root";
    static String password="28730";
    public static ContactModel readContactFromDatabase(String id) throws SQLException {
        ContactModel contactModel=null;
        Connection connection= DriverManager.getConnection(url,username,password);
        System.out.println(("Connection successfully"));
        String query="Select * from contacts where id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setString(1,id);
        ResultSet resultSet=preparedStatement.executeQuery();
        if(resultSet.next()){
            contactModel=new ContactModel();
            contactModel.setId(resultSet.getString("id"));
            contactModel.setName(resultSet.getString("name"));
            contactModel.setLastName(resultSet.getString("lastName"));
            contactModel.setEmail(resultSet.getString("email"));
            contactModel.setPhone(resultSet.getString("phone"));
            contactModel.setAddress(resultSet.getString("address"));
            contactModel.setDescription(resultSet.getString("description"));

        }else{
            System.out.println("No contact"+id);
        }
        preparedStatement.close();
        connection.close();
        return contactModel;
    }
    //*****************
    public static List<ContactModel> readAllContactsFromDatabase() throws SQLException {
        List<ContactModel> contacts=new ArrayList<>();

//not used!!!
        Connection connection= DriverManager.getConnection(url,username,password);
        System.out.println(("Connection successfully"));
        String query="Select * from contacts";

        Statement preparedStatement=connection.createStatement();

        ResultSet resultSet=preparedStatement.executeQuery(query);

        if(resultSet.next()){
            ContactModel contactModel=new ContactModel();

            contactModel.setId(resultSet.getString("id"));
            contactModel.setName(resultSet.getString("name"));
            contactModel.setLastName(resultSet.getString("lastName"));
            contactModel.setEmail(resultSet.getString("email"));
            contactModel.setPhone(resultSet.getString("phone"));
            contactModel.setAddress(resultSet.getString("address"));
            contactModel.setDescription(resultSet.getString("description"));
            contacts.add(contactModel);
        }
        preparedStatement.close();
        connection.close();
        return contacts;
    }
    public static void main(String[] args) throws SQLException {
        //first
//        ContactModel contact=readContactFromDatabase("fcdaa349-7c14-4c18-a1f9-424be91dae21");
//        System.out.println("Contact name"+contact.getName());
//        System.out.println("An email"+contact.getEmail());
        //second
        List<ContactModel> cont=readAllContactsFromDatabase();
        for(ContactModel contactModel:cont){
            System.out.println("Record: "+contactModel.getName());
        }
    }
}
