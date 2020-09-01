package com.aforo255.ms.test.pay.dao;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.aforo255.ms.test.pay.domain.TransactionRedis;

@Repository
public class TransactionRedisDao implements ITransactionRedis{
	
	@Autowired
	private RedisTemplate<String , TransactionRedis> redisTemplate;
	private HashOperations hashOperation;
	
	String key="TRANSACTION";
	@PostConstruct
	private void init() {
		hashOperation =  redisTemplate.opsForHash();
	}

	@Override
	public void save(TransactionRedis transaction) {
		// TODO Auto-generated method stub
		hashOperation.put(key, transaction.getId(), transaction);
	}

	@Override
	public Map<String, TransactionRedis> findAll() {
		// TODO Auto-generated method stub
		return hashOperation.entries(key);
	}

	@Override
	public TransactionRedis findById(String id) {
		// TODO Auto-generated method stub
		return (TransactionRedis) hashOperation.get(key, id);
	}

	@Override
	public void update(TransactionRedis transaction) {
		// TODO Auto-generated method stub
		save(transaction);
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		hashOperation.delete(key, id);
		
	}

}
