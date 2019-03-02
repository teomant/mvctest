
package org.teomant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.teomant.soap.DateTimeClient;

@Configuration
public class DateTimeConfiguration {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("org.teomant.server");
		return marshaller;
	}

	@Bean
	public DateTimeClient countryClient(Jaxb2Marshaller marshaller) {
		DateTimeClient client = new DateTimeClient();
		client.setDefaultUri("http://localhost:8080/server");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}
