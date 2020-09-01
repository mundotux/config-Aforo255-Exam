package com.aforo255.ms.test.transaction.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.aforo255.ms.test.transaction.entity.Transaction;

public interface ITransactionRepository extends MongoRepository<Transaction, String>{
	
	@Query("{'idInvoice':?0}")
	public Iterable<Transaction> findByIdInvoice(Integer idInvoice);

}
