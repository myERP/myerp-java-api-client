package com.myerp.api.examples;

import com.myerp.api.MyERPClient;
import com.myerp.api.exceptions.MyERPException;
import com.myerp.api.objects.AccountingEntry;
import com.myerp.api.objects.Transaction;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class ExampleTransactions {

  public static final String API_EMAIL = ".................";
  public static final String API_KEY = ".................";

  public static void main(final String[] args) throws MyERPException, IOException {

    // initiate a client
    final MyERPClient client = new MyERPClient(API_EMAIL, API_KEY);

    Transaction t = new Transaction();
    t.piece_number = "CINV-237";
    t.date = new Date();

    AccountingEntry e = new AccountingEntry();
    e.label = "Invoice Payment";
    e.amount = 17578.65;
    e.way = "debit";
    e.account_id = 21;

    AccountingEntry e1 = new AccountingEntry();
    e1.label = "Invoice Payment";
    e1.amount = 17578.65;
    e1.way = "credit";
    e1.account_id = 85;

    t.entries = Arrays.asList(e, e1);
    t = client.transactionsAPI().save(t);
    System.out.println("Transaction created [id=" + t.id + ", date=" + t.date + ", entries=" + t.entries + "]");

  }
}
