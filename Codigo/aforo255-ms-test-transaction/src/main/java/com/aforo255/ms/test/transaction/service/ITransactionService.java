package com.aforo255.ms.test.transaction.service;

import com.aforo255.ms.test.transaction.entity.Transaction;

public interface ITransactionService {
	
	public Transaction save(Transaction transaction);
	public Iterable<Transaction> findByIdInvoice(Integer invoiceId);
	public Iterable<Transaction> findAll();

}
