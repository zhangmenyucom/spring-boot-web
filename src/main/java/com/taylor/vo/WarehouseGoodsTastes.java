package com.taylor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseGoodsTastes implements Serializable {
	private static final long serialVersionUID = 4808450438124961718L;
	private List<WarehouseGoodsTastesGroup> orderGoodsTastes;
}
