package com.aforo255.ms.test.pay.dao;

import java.util.Map;

import com.aforo255.ms.test.pay.domain.TransactionRedis;

public interface ITransactionRedis {

	
	void save(TransactionRedis transaction);

	Map<String, TransactionRedis> findAll();

	TransactionRedis findById(String id);

	void update(TransactionRedis transaction);

	void delete(String id);
	
}
