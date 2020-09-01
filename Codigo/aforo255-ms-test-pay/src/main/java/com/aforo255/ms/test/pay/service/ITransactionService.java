package com.aforo255.ms.test.pay.service;

import java.util.Map;

import com.aforo255.ms.test.pay.domain.Transaction;
import com.aforo255.ms.test.pay.domain.TransactionRedis;

public interface ITransactionService {
	
	public Transaction findById(Integer idOperation );
	public Transaction save (Transaction transaction);
	
	public TransactionRedis findByIdRedis(String id);
	public void save(TransactionRedis transaction);
	public Map<String, TransactionRedis> findAll();

}
