package com.aforo255.ms.test.pay.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aforo255.ms.test.pay.domain.Transaction;

@Repository
public interface TransactionDao extends CrudRepository<Transaction, Integer>{

}
