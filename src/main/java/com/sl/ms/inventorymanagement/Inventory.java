package com.sl.ms.inventorymanagement;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import net.minidev.json.JSONArray;

@Entity(name = "SL_INV")
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date date;

	@Lob
	String file;

	/**
	 * @param id
	 * @param date
	 * @param string
	 */
	public Inventory(Long id, Date date, JSONArray string) {
		super();
		this.id = id;
		this.date = date;
		this.file = string.toJSONString();
	}

	public Inventory() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the file
	 */
	public String getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(String file) {
		this.file = file;
	}

}
