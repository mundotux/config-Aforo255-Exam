package com.aforo255.ms.test.pay.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aforo255.ms.test.pay.dao.ITransactionRedis;
import com.aforo255.ms.test.pay.dao.TransactionDao;
import com.aforo255.ms.test.pay.dao.TransactionRedisDao;
import com.aforo255.ms.test.pay.domain.Transaction;
import com.aforo255.ms.test.pay.domain.TransactionRedis;


@Service
public class TransactionServiceImpl implements ITransactionService{

	@Autowired
	private TransactionDao transactionDao;
	
	@Autowired
	private ITransactionRedis transactionRedis;
	

	@Override
	public Transaction findById(Integer idOperation) {
		// TODO Auto-generated method stub
		return transactionDao.findById(idOperation).orElse(null);
	}

	@Override
	public Transaction save(Transaction transaction) {
		// TODO Auto-generated method stub
		return transactionDao.save(transaction);
	}

	@Override
	public TransactionRedis findByIdRedis(String id) {
		// TODO Auto-generated method stub
		transactionRedis.findById(id);
		return null;
	}

	@Override
	public void save(TransactionRedis transaction) {
		// TODO Auto-generated method stub
		transactionRedis.save(transaction);
	}

	@Override
	public Map<String, TransactionRedis> findAll() {
		// TODO Auto-generated method stub
		return transactionRedis.findAll();
	}

}
