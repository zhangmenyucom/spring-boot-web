package com.taylor.common;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author haoli
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class PageResultBean<T> extends PageBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6417879587117160810L;
	private long totalCount;
	private long totalPage;
	private List<T> items;
	
	/**
	 * @see getTotalCount()
	 * @return
	 */
	public long getCount(){
		return getTotalCount();
	}
}
