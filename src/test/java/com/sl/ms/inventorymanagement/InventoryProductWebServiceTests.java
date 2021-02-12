package com.sl.ms.inventorymanagement;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.minidev.json.JSONArray;

@WebMvcTest(InventoryProductWebServices.class)
public class InventoryProductWebServiceTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean ProductRepository pr;
	@MockBean InventoryRepository ir;
	Product p = new Product((long) 1, "Product1", 12.50, 12);
	Inventory i = new Inventory( new Date("01/01/2021"), new JSONArray());
    List<Product> prds = new ArrayList<Product>();
    
    @Test void shouldUploadInventory() {
      Mockito.when(ir.save(Mockito.any())).thenReturn(i);
      MockMultipartFile file 
      = new MockMultipartFile(
        "file", 
        "hello.txt", 
        MediaType.TEXT_PLAIN_VALUE, 
       "[{ \"id\":2,\"name\":\"Product20\",\"price\": 400,\"quantity\":4},{\"id\":3,\"name\":\"Product12\",\"price\": 14,\"quantity\":1}]".getBytes()
//"s".getBytes()
    		  );
	try {
		this.mockMvc.perform(multipart("/products/file")//.contentType("multipart/form-data")
				.file(file))
		.andExpect(status().isOk());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}