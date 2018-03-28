package com.taylor.common;

/**
 * @author Administrator
 */

public enum KLineTypeEnum {

	ONE_MINI(1, "1分钟k线"),
	FIVE_MINI(2, "5分钟k线"),
	FIVETEEN_MINI(3, "15分钟k线"),
	THIRTY_MINI(4, "30分钟k线"),
	SIXTY_MINI(5, "60分钟k线");

	private Integer key;

	private String description;

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	KLineTypeEnum(Integer key, String description) {
		this.key = key;
		this.description = description;
	}

}
