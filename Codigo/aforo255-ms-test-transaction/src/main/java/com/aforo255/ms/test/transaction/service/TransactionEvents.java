package com.aforo255.ms.test.transaction.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aforo255.ms.test.transaction.entity.Transaction;
import com.aforo255.ms.test.transaction.repository.ITransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TransactionEvents {


	@Autowired
	private ITransactionRepository repository;

	@Autowired
	ObjectMapper objectMapper;
	
	private Logger log = LoggerFactory.getLogger(TransactionEvents.class);
	
	public void processTransactionEvent (ConsumerRecord<Integer, String> consumerRecord) throws JsonMappingException, JsonProcessingException {

		
		Transaction event = objectMapper.readValue(consumerRecord.value(), Transaction.class);
		log.info("transactionEvent: {} ", event.getIdInvoice());
		repository.save(event);	
		
	}
	
}


