package com.aforo255.ms.test.pay.producer;

import java.util.List;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.aforo255.ms.test.pay.domain.Transaction;
import com.aforo255.ms.test.pay.domain.TransactionRedis;
import com.aforo255.ms.test.pay.service.ITransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PaymentEventProducer {
	
	String topic = "payments-events";
	private Logger log = LoggerFactory.getLogger(PaymentEventProducer.class);
	@Autowired
	KafkaTemplate<Integer, String> kafkaTemplate;
	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	private ITransactionService service;

	public ListenableFuture <SendResult<Integer, String>> sendPaymentEvent(Transaction paymentEvent) throws JsonProcessingException{
		
		Integer key = paymentEvent.getIdOperation();
		String value = objectMapper.writeValueAsString(paymentEvent);
		
		ProducerRecord<Integer, String> producerRecord= buildProducerRecord(key,value,topic);
		
		ListenableFuture<SendResult<Integer, String>> listenableFuture=kafkaTemplate.send(producerRecord);
		
		listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {

			@Override
			public void onSuccess(SendResult<Integer, String> result) {
				// TODO Auto-generated method stub
				try {
					handleSuccess(key,value,result);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Throwable ex) {
				// TODO Auto-generated method stub
				handleFailure(key,value,ex);
				
			}
		} );
		
		return listenableFuture;
	}
	
	private ProducerRecord<Integer, String> buildProducerRecord(Integer key , String value,String topic){
		
		// agregando header
		List<Header> recordHeaders = List.of(new RecordHeader("payment-event-source", "scanner".getBytes()));
		return new ProducerRecord<>(topic,null,key , value,recordHeaders);
		
	}
	
	
	
	private void handleFailure(Integer key, String value, Throwable ex) {
		log.error("Error Sending the Message and the execpition is {}", ex.getMessage());
		try {
			throw ex;
		} catch (Throwable throwable) {
			log.error("Error in OnFailure : {}", throwable.getMessage());
		}
	}

	private void handleSuccess(Integer key, String value, SendResult<Integer, String> result) throws JsonMappingException, JsonProcessingException {
		
		TransactionRedis transactionRedis = objectMapper.readValue(value, TransactionRedis.class);
		service.save(transactionRedis);
    
		log.info("Message Sent SuccessFully for the key : {} and the value is {} , partition is {}",
				key , value , result.getRecordMetadata().partition());
		
	}


}
