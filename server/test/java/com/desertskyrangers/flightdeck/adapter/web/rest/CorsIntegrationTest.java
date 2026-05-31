package com.desertskyrangers.flightdeck.adapter.web.rest;

import com.desertskyrangers.flightdeck.adapter.web.ApiPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CorsIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void whenOptionsRequest_thenReturnsCorsHeaders() throws Exception {
		this.mockMvc.perform( options( ApiPath.AUTH_LOGIN )
				.header( "Access-Control-Request-Method", "POST" )
				.header( "Origin", "http://localhost:5173" ) )
			.andDo( result -> {
				System.out.println( "[DEBUG_LOG] Status: " + result.getResponse().getStatus() );
				System.out.println( "[DEBUG_LOG] Headers: " + result.getResponse().getHeaderNames() );
				for( String name : result.getResponse().getHeaderNames() ) {
					System.out.println( "[DEBUG_LOG] Header " + name + ": " + result.getResponse().getHeader( name ) );
				}
			} )
			.andExpect( status().isOk() )
			.andExpect( header().string( "Access-Control-Allow-Origin", "*" ) )
			.andExpect( header().string( "Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS" ) );
	}
}
