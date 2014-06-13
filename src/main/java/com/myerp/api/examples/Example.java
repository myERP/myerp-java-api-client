package com.myerp.api.examples;

import com.myerp.api.MyERPClient;
import com.myerp.api.exceptions.MyERPException;
import com.myerp.api.objects.Address;
import com.myerp.api.objects.Contact;
import com.myerp.api.objects.Customer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Example {

  public static final String API_EMAIL = ".....";
  public static final String API_KEY = ".......";

  public static void main(final String[] args) throws MyERPException, IOException {

    // initiate a client
    final MyERPClient client = new MyERPClient(API_EMAIL, API_KEY);

    // retrieve all the customers
    List<Customer> customers = client.customersAPI().findAll();
    for (Customer customer : customers) {
      System.out.println(customer.full_name + " [id=" + customer.id + "]");
    }

    // retrieve a customer
    Customer customer = client.customersAPI().find(261366);
    System.out.println(customer.full_name + " [id=" + customer.id + "]");

    // create a customer
    // all the available attributes can be find in the documentation http://developers.myerp.com/docs/1.0/api/
    Customer john = new Customer();
    john.status = 1;
    john.type = 2;
    john.first_name = "John";
    john.last_name = "Doe";
    john.address = new Address();
    john.address.label1 = "1 powell street";
    john.address.zipcode = "94102";
    john.address.city = "San Francisco";
    john.address.country = "United States";
    john.address.localisation = "California";
    Contact contact1 = new Contact();
    contact1.first_name = "Jane";
    contact1.last_name = "Doe";
    john.contacts = Arrays.asList(contact1);
    john = client.customersAPI().save(john); //save
    System.out.println(john.full_name + " created [id=" + john.id + ", contacts=" + john.contacts + "]");

    // update a customer
    john.first_name = "Johnny";
    john.contacts.get(0).first_name = "Martha";
    john = client.customersAPI().save(john);
    System.out.println(john.full_name + " updated [id=" + john.id + ", contacts=" + john.contacts + "]");

    // delete a customer
    john = client.customersAPI().delete(john);
    System.out.println(john.full_name + " deleted [id=" + john.id + "]");

    Customer ken = new Customer();
    ken.status = 1;
    ken.type = 2;
    ken.first_name = "Ken";
    ken.last_name = "Doe";

    Customer lisa = new Customer();
    lisa.status = 1;
    lisa.type = 2;
    lisa.first_name = "Lisa";
    lisa.last_name = "Doe";
  }
}
