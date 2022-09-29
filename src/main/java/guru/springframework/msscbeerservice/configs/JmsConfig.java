package guru.springframework.msscbeerservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import lombok.AllArgsConstructor;
@Configuration
@AllArgsConstructor
public class JmsConfig {
	
	private static final String BEER_SERVICE_QUEUE ="beerservicequeue";
	
	@Bean
	public MessageConverter  messageConverter() {
		MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
		messageConverter.setTargetType(MessageType.TEXT);
		messageConverter.setTypeIdPropertyName("_type");	
		return messageConverter;
	}

}
