package com.sl.ms.inventorymanagement;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ProductWebServices.class)
public class ProductWebServiceTests {

	@Autowired
	private MockMvc mockMvc;

//	@MockBean ProductWebServices pws;
//	@MockBean InventoryRepository ir;

	@MockBean ProductRepository pr;
	Product p = new Product((long) 1, "Product1", 12.50, 12);
    List<Product> prds = new ArrayList<Product>();
	@Test
	public void shouldReturnAllProducts() throws Exception {
		prds.add(p);
		Mockito.when(pr.findAll()).thenReturn(prds);

		System.out.println("get:" + pr.findById((long) 1));
		this.mockMvc.perform(get("/products")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Product1")));

	}

	@Test
	public void shouldReturnGivenOrder() throws Exception {

		Mockito.when(pr.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(p));
		this.mockMvc.perform(get("/products/1")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Product1")));
	}

	@Test
	public void shouldAddNewOrder() throws Exception {

		Product prd = new Product((long) 3, "Product3", (double) 100, 2);
		Mockito.when(pr.save(Mockito.any())).thenReturn(prd);

		this.mockMvc.perform(post("/products/3").contentType("application/json").content(asJsonString(prd)))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Product3")));
	}

	@Test
	public void shouldPutNewProduct() throws Exception {

		Product prd = new Product((long) 3, "Product3", (double) 100, 2);
		Mockito.when(pr.save(Mockito.any())).thenReturn(prd);

		this.mockMvc.perform(put("/products/3").contentType("application/json").content(asJsonString(prd)))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Product3")));
	}
	
	@Test
	public void shouldPostAllProducts() throws Exception {
		prds.add(p);
		Product prd = new Product((long) 3, "Product3", (double) 100, 2);
		prds.add(prd);
		Mockito.when(pr.saveAll(Mockito.any())).thenReturn(prds);

		this.mockMvc.perform(post("/products").contentType("application/json").content(asJsonString(prds)))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Product3")));
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

	@Test
	public void shouldDeleteGivenOrder() throws Exception {
		Mockito.when(pr.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(p));
		doNothing().when(pr).deleteById(Mockito.anyLong());
		this.mockMvc.perform(delete("/products/1")).andDo(print()).andExpect(status().isOk());
	}

}