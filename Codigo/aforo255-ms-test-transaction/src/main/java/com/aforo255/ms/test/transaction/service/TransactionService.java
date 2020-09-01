package com.aforo255.ms.test.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aforo255.ms.test.transaction.entity.Transaction;
import com.aforo255.ms.test.transaction.repository.ITransactionRepository;

@Service
public class TransactionService implements ITransactionService{
	
	@Autowired
	ITransactionRepository repository;

	
	@Override
	public Transaction save(Transaction transaction) {
		// TODO Auto-generated method stub
		return repository.save(transaction);
	}

	@Override
	public Iterable<Transaction> findByIdInvoice(Integer invoiceId) {
		// TODO Auto-generated method stub
		return repository.findByIdInvoice(invoiceId);
	}

	@Override
	public Iterable<Transaction> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

}
