package com.taylor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

@Data
@Table(name = "amq")
@AllArgsConstructor
@NoArgsConstructor
public class AmqEntity {

	public AmqEntity(Integer mqType, String message) {
		this.mqType = mqType;
		this.message = message;
	}

	private Integer id;

	private Integer mqType;

	private String message;

}
