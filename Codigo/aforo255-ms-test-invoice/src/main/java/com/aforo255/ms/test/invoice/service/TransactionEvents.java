package com.aforo255.ms.test.invoice.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aforo255.ms.test.invoice.entity.Invoice;
import com.aforo255.ms.test.invoice.entity.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TransactionEvents {

	@Autowired
	private IInvoiceService repository;

	@Autowired
	ObjectMapper objectMapper;
	
	private Logger log = LoggerFactory.getLogger(TransactionEvents.class);
	
	public void processTransactionEvent (ConsumerRecord<Integer, String> consumerRecord) throws JsonMappingException, JsonProcessingException {
		
		Invoice invoice = new Invoice();
		
		Transaction event = objectMapper.readValue(consumerRecord.value(), Transaction.class);
		log.info("transactionEvent: {} ", event.getIdInvoice());
		invoice = repository.findById(event.getIdInvoice());
		invoice.setState(1);
		log.info("Se ha pagado la factura # " + invoice.getIdInvoice());
		repository.save(invoice);	
		
	}

}
