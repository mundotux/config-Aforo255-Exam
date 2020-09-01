package com.aforo255.ms.test.pay.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aforo255.ms.test.pay.domain.Transaction;
import com.aforo255.ms.test.pay.domain.TransactionRedis;
import com.aforo255.ms.test.pay.producer.PaymentEventProducer;
import com.aforo255.ms.test.pay.service.ITransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class PaymentEventController {

	private Logger log = LoggerFactory.getLogger(PaymentEventController.class);
	@Autowired
	PaymentEventProducer paymentProducer ;
	@Autowired
	private ITransactionService transactionService;
	
	@PostMapping("/v1/paymentEvent")
	public ResponseEntity<Transaction> postDepositEvent (@RequestBody Transaction transactionEvent) throws JsonProcessingException{
		log.info("antes de tranSql");
		Transaction tranSql = transactionService.save(transactionEvent);
		log.info("despues de tranSql");
		log.info("antes  de sendPaymentEvent");
		paymentProducer.sendPaymentEvent(tranSql);
		log.info("despues  de sendPaymentEvent");
		return  ResponseEntity.status(HttpStatus.CREATED).body(tranSql);				
	}
	
	@GetMapping("/all")
	public Map<String, TransactionRedis> all() {
		return transactionService.findAll();
	}
	
}
