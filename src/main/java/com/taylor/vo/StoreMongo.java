package com.taylor.vo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection="store")
@EqualsAndHashCode(callSuper = false)
public class StoreMongo extends StoreWithAddress implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1676685364287686063L;
	
	@Id
	private long id;
	private double[]  position;
	private String cover;
	private Integer isCan;
	private Integer isCard;
	private Integer isWai;
	private double distance;
	private long storeExtId;
	private Integer isOfferPay;

}