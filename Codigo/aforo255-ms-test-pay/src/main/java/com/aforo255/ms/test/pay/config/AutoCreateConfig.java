package com.aforo255.ms.test.pay.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class AutoCreateConfig {

	public NewTopic payEvent() {
		
		return TopicBuilder.name("payments-events")
					.partitions(3)
					.replicas(1)
					.build();
		
	}
}
