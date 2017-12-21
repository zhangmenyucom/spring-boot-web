package com.taylor.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 为了同时兼容目前返回数据的两种方式（ModelAndView&CommonResponse）
 * ,同时为了更好地与springmvc容器集成，将CommonResponse改为继承ModelAndView
 *
 * @author taylor
 * @param <T> 自定义的对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -7921893893735709488L;

	private int errorcode;
	private String errormsg;
	T data ;


}
