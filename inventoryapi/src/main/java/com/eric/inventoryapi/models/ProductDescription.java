package com.eric.inventoryapi.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Embeddable
@Data
public class ProductDescription {
    @Column(name="Product_Name",nullable = false,length = 100)
	private String productName;
    @Column(name="DOP")
    @DateTimeFormat(iso = ISO.DATE)
	private LocalDate dop;
    @Column(name="Cost")
	private long cost;
}
