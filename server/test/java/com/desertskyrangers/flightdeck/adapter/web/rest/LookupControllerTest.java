package com.desertskyrangers.flightdeck.adapter.web.rest;

import com.desertskyrangers.flightdeck.adapter.web.ApiPath;
import com.desertskyrangers.flightdeck.adapter.web.model.ReactOption;
import com.desertskyrangers.flightdeck.core.model.Aircraft;
import com.desertskyrangers.flightdeck.core.model.AircraftType;
import com.desertskyrangers.flightdeck.core.model.Battery;
import com.desertskyrangers.flightdeck.core.model.Group;
import com.desertskyrangers.flightdeck.util.Json;
import com.desertskyrangers.flightdeck.util.SmsCarrier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
@SpringBootTest
public class LookupControllerTest extends BaseControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetAircraftStatuses() throws Exception {
		List<ReactOption> response = Arrays.stream( Aircraft.Status.values() ).map( c -> new ReactOption( c.name().toLowerCase(), c.getName() ) ).toList();
		this.mockMvc.perform( MockMvcRequestBuilders.get( ApiPath.AIRCRAFT_STATUS ).with( jwt() ) ).andExpect( status().isOk() ).andExpect( content().json( Json.stringify( response ), true ) );
	}

	@Test
	void testGetAircraftTypes() throws Exception {
		List<ReactOption> response = Arrays.stream( AircraftType.values() ).map( c -> new ReactOption( c.name().toLowerCase(), c.getName() ) ).toList();
		this.mockMvc.perform( get( ApiPath.AIRCRAFT_TYPE ).with( jwt() ) ).andExpect( status().isOk() ).andExpect( content().json( Json.stringify( response ), true ) );
	}

	@Test
	void testGetBatteryConnectors() throws Exception {
		List<ReactOption> response = Arrays.stream( Battery.Connector.values() ).map( c -> new ReactOption( c.name().toLowerCase(), c.getName() ) ).toList();
		this.mockMvc.perform( MockMvcRequestBuilders.get( ApiPath.BATTERY_CONNECTOR ).with( jwt() ) ).andExpect( status().isOk() ).andExpect( content().json( Json.stringify( response ), true ) );
	}

	@Test
	void testGetBatteryStatuses() throws Exception {
		List<ReactOption> response = Arrays.stream( Battery.Status.values() ).map( c -> new ReactOption( c.name().toLowerCase(), c.getName() ) ).toList();
		this.mockMvc.perform( MockMvcRequestBuilders.get( ApiPath.BATTERY_STATUS ).with( jwt() ) ).andExpect( status().isOk() ).andExpect( content().json( Json.stringify( response ), true ) );
	}

	@Test
	void testGetBatteryTypes() throws Exception {
		List<ReactOption> response = Arrays.stream( Battery.Chemistry.values() ).map( c -> new ReactOption( c.name().toLowerCase(), c.getName() ) ).toList();
		this.mockMvc.perform( get( ApiPath.BATTERY_TYPE ).with( jwt() ) ).andExpect( status().isOk() ).andExpect( content().json( Json.stringify( response ), true ) );
	}

	@Test
	void testGetGroupTypes() throws Exception {
		List<ReactOption> response = Arrays.stream( Group.Type.values() ).map( c -> new ReactOption( c.name().toLowerCase(), c.getName() ) ).toList();
		this.mockMvc.perform( get( ApiPath.GROUP_TYPE ).with( jwt() ) ).andExpect( status().isOk() ).andExpect( content().json( Json.stringify( response ), true ) );
	}

	@Test
	void testGetSmsCarriers() throws Exception {
		List<ReactOption> response = Arrays.stream( SmsCarrier.values() ).map( c -> new ReactOption( c.name().toLowerCase(), c.getName() ) ).toList();
		this.mockMvc.perform( get( ApiPath.SMS_CARRIERS ).with( jwt() ) ).andExpect( status().isOk() ).andExpect( content().json( Json.stringify( response ), true ) );
	}

}
