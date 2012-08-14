package gabriel.atrack.spring.conf.si.jms;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.jms.connection.CachingConnectionFactory;

//@Configuration
public class IntegrationJMSConfiguration {

	

	@Bean
	public CachingConnectionFactory cachingConnectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
		cachingConnectionFactory
				.setTargetConnectionFactory(activeMQConnectionFactory());
		cachingConnectionFactory.setSessionCacheSize(10);
		cachingConnectionFactory.setCacheProducers(false);
		return cachingConnectionFactory;
	}

	@Bean
	public ActiveMQConnectionFactory activeMQConnectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL("tcp://localhost:61616");
		return activeMQConnectionFactory;
	}

}