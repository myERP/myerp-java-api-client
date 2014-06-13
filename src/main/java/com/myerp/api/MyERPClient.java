package com.myerp.api;

import com.myerp.api.apis.AccountsAPI;
import com.myerp.api.apis.CurrenciesAPI;
import com.myerp.api.apis.CustomersAPI;
import com.myerp.api.apis.ItemFamiliesAPI;
import com.myerp.api.apis.ItemsAPI;
import com.myerp.api.apis.PaymentTermsAPI;
import com.myerp.api.apis.ProjectsAPI;
import com.myerp.api.apis.SalesOrdersAPI;
import com.myerp.api.apis.TransactionsAPI;

public class MyERPClient {

  private String apiEmail;

  private String apiKey;

  private String endpoint = "https://api.myerp.com/v1";

  private CustomersAPI customersAPI = null;

  private ProjectsAPI projectsAPI = null;

  private ItemsAPI itemsAPI = null;

  private ItemFamiliesAPI itemFamiliesAPI = null;

  private SalesOrdersAPI salesOrdersAPI = null;

  private TransactionsAPI transactionsAPI = null;

  private AccountsAPI accountsAPI = null;

  private PaymentTermsAPI paymentTermsAPI = null;

  private CurrenciesAPI currenciesAPI = null;

  public MyERPClient(String apiEmail, String apiKey) {
    this(apiEmail, apiKey, null);
  }

  public MyERPClient(String apiEmail, String apiKey, String endpoint) {
    this.apiEmail = apiEmail;
    this.apiKey = apiKey;
    if (endpoint != null) {
      this.endpoint = endpoint;
    }
  }

  public CustomersAPI customersAPI() {
    if (this.customersAPI != null) {
      return this.customersAPI;
    }
    this.customersAPI = new CustomersAPI(this.apiEmail, this.apiKey, this.endpoint);
    return this.customersAPI;
  }

  public ProjectsAPI projectsAPI() {
    if (this.projectsAPI != null) {
      return this.projectsAPI;
    }
    this.projectsAPI = new ProjectsAPI(this.apiEmail, this.apiKey, this.endpoint);
    return this.projectsAPI;
  }

  public ItemsAPI itemsAPI() {
    if (this.itemsAPI != null) {
      return this.itemsAPI;
    }
    this.itemsAPI = new ItemsAPI(this.apiEmail, this.apiKey, this.endpoint);
    return this.itemsAPI;
  }

  public ItemFamiliesAPI itemFamiliesAPI() {
    if (this.itemFamiliesAPI != null) {
      return this.itemFamiliesAPI;
    }
    this.itemFamiliesAPI = new ItemFamiliesAPI(this.apiEmail, this.apiKey, this.endpoint);
    return this.itemFamiliesAPI;
  }

  public SalesOrdersAPI salesOrdersAPI() {
    if (this.salesOrdersAPI != null) {
      return this.salesOrdersAPI;
    }
    this.salesOrdersAPI = new SalesOrdersAPI(this.apiEmail, this.apiKey, this.endpoint);
    return this.salesOrdersAPI;
  }

  public TransactionsAPI transactionsAPI() {
    if (this.transactionsAPI != null) {
      return this.transactionsAPI;
    }
    this.transactionsAPI = new TransactionsAPI(this.apiEmail, this.apiKey, this.endpoint);
    return this.transactionsAPI;
  }

  public AccountsAPI accountsAPI() {
    if (this.accountsAPI != null) {
      return this.accountsAPI;
    }
    this.accountsAPI = new AccountsAPI(this.apiEmail, this.apiKey, this.endpoint);
    return this.accountsAPI;
  }

  public PaymentTermsAPI paymentTermsAPI() {
    if (this.paymentTermsAPI != null) {
      return this.paymentTermsAPI;
    }
    this.paymentTermsAPI = new PaymentTermsAPI(this.apiEmail, this.apiKey, this.endpoint);
    return this.paymentTermsAPI;
  }

  public CurrenciesAPI currenciesAPI() {
    if (this.currenciesAPI != null) {
      return this.currenciesAPI;
    }
    this.currenciesAPI = new CurrenciesAPI(this.apiEmail, this.apiKey, this.endpoint);
    return this.currenciesAPI;
  }
}
