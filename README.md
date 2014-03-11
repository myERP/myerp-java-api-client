# myERP API

[![Build Status](https://travis-ci.org/myERP/myerp-java-api-client.png?branch=master)](https://travis-ci.org/myERP/myerp-java-api-client)

A Java client library for [myERP's API](http://developers.myerp.com).

## Installation Using [Maven](http://maven.org/)
Use the following dependency in your project:

    available soon

If you want to compile it yourself, here's how:

    $ git clone git@github.com:myerp/myerp-java-api-client
    $ cd myerp-java-api-client
    $ mvn install    # Requires maven, download from http://maven.apache.org/download.html


## Getting Started

- 1 - Retrieve your API_KEY and your API_EMAIL from the API settings. More information [here](http://developers.myerp.com/docs/1.0/overview/security_authentication.html).

- 2 - Here are some examples about the customer API

```java
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
    Customer customer = client.customersAPI().find(1337);
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

    // bulk creation/modification
    List<Customer> bulkCustomers = client.customersAPI().save(Arrays.asList(new Customer[] { ken, lisa }));
    for (Customer cus : bulkCustomers) {
      System.out.println(cus.full_name + " created [id=" + cus.id + "]");
    }

    // bulk deletion
    customers = client.customersAPI().delete(bulkCustomers);
    for (Customer cus : customers) {
      System.out.println(cus.full_name + " deleted [id=" + cus.id + "]");
    }

  }
}
```

## Exceptions
If the myERP API returns a 400 or a 500 level HTTP response, the library will throw a MyERPException. All the exception should be handled appropriately.

```java

// retrieve a customer
try {
  Customer customer = client.customersAPI().find(1337);
  System.out.println(customer.full_name + " [id=" + customer.id + "]");
} catch (NotFoundException e) {
  System.err.println(e);
}


```

## Contributing

Thanks for considering contributing to this project.

### Finding something to do

Ask, or pick an issue and comment on it announcing your desire to work on it. Ideally wait until we assign it to you to minimize work duplication.

### Reporting an issue

- Search existing issues before raising a new one.

- Include as much detail as possible.

### Pull requests

- Make it clear in the issue tracker what you are working on, so that someone else doesn't duplicate the work.

- Use a feature branch, not master.

- Rebase your feature branch onto origin/master before raising the PR.

- Keep up to date with changes in master so your PR is easy to merge.

- Be descriptive in your PR message: what is it for, why is it needed, etc.

- Make sure the tests pass

- Squash related commits as much as possible.

### Coding style

- Try to match the existing indent style.

- Don't abuse the pre-processor.

- Don't mix platform-specific stuff into the main code.


## License

The myERP API wrapper is released under the [MIT License](http://www.opensource.org/licenses/MIT).
