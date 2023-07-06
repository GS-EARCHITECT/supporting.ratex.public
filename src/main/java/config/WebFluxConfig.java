package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Configuration
public class WebFluxConfig implements WebFluxConfigurer 
{
	private ClientHttpConnector connector() {
	    return new 
	 ReactorClientHttpConnector(HttpClient.create(ConnectionProvider.newConnection()));
	}
	
	@Bean
	public WebClient getWebClient()
	{
				return WebClient.builder().clientConnector(connector())
		        .baseUrl("http://localhost:9912")		
		        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
		        .build();
	}
	
}