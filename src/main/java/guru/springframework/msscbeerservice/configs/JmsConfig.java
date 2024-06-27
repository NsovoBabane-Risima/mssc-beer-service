package guru.springframework.msscbeerservice.configs;

import javax.jms.Destination;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class JmsConfig {

	private static final String BEER_SERVICE_QUEUE = "beerservicequeue";
	public static final String BREWING_REQUEST_QUEUE = "brewing-request";
	public static final String NEW_INVENTORY_QUEUE = "new_inventory";
	public static final String VALIDATE_ORDER_QUEUE = "validate-order";
	public static final String  VALIDATE_ORDER_RESPONSE_QUEUE = "validate-order-result";

	/*
	 * Spring will provide the Spring managed Jackson ObjectMapper to help manage
	 * with mapping of objects from json to the respective objects
	 * 
	 */

	@Bean
	public MessageConverter messageConverter(ObjectMapper objectMapper) {
		MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
		messageConverter.setTargetType(MessageType.TEXT);
		messageConverter.setTypeIdPropertyName("_type");
		messageConverter.setObjectMapper(objectMapper);
		return messageConverter;
	}

}
