package com.taylor.vo;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.*;

/**
 *对应实体类
 * @author taylor
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Data
public class OnlineOrderExtBooking implements Serializable {

	private static final long serialVersionUID = -5051287824365233270L;

	/**  */
	private Long id;
	/**  */
	private Long merchantId;
	/**  */
	private Long storeId;
	/** 预定时间设置 */
	private String weekTime;
	/** 是否包房 */
	private Byte rooms;
	/** 多少人起订 */
	private Integer mininum;
	/** 保留小时 */
	private Integer reserveHour;
	/** 保留分钟 */
	private Integer reserveMinute;
	/** 版本 */
	private Integer version;
	/** 状态 */
	private Integer status;
	/**  */
	private Date createTime;
	/**  */
	private Date updateTime;

	public Map<Integer,Set<String>> getTimesWithAllWeek(){
		Map<Integer,Set<String>> result = new HashMap<>();
		List<String> times = Arrays.asList(
				"00:00","00:30","01:00","01:30","02:00","02:30","03:00","03:30",
				"04:00","04:30","05:00","05:30","06:00","06:30","07:00","07:30",
				"08:00","08:30","09:00","09:30","10:00","10:30","11:00","11:30",
				"12:00","12:30","13:00","13:30","14:00","14:30","15:00","15:30",
				"16:00","16:30","17:00","17:30","18:00","18:30","19:00","19:30",
				"20:00","20:30","21:00","21:30","22:00","22:30","23:00","23:30");


		if(!weekTime.isEmpty()){
			List<BookingWeeksTimes> weektimeList = JSON.parseArray(weekTime,BookingWeeksTimes.class);
			for (BookingWeeksTimes wt:weektimeList) {
				if(!wt.getWeeks().isEmpty()){
					for(int w:wt.getWeeks()){
						Set t = new TreeSet(times.subList(wt.getFrom(),wt.getTo()+1));
						Set _t =  result.get(w);
						if(_t==null){
							result.put(w,t);
						}else{
							_t.addAll(t);
							result.put(w,_t);
						}
					}
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		OnlineOrderExtBooking booking = new OnlineOrderExtBooking();
		BookingWeeksTimes weeksTimes = new BookingWeeksTimes();
		weeksTimes.setFrom(0);
		weeksTimes.setTo(4);
		weeksTimes.setWeeks(Arrays.asList(1,2,3));
		List<BookingWeeksTimes> weektimeList = new ArrayList<>();
		weektimeList.add(weeksTimes);

		BookingWeeksTimes weeksTimes2 = new BookingWeeksTimes();
		weeksTimes2.setFrom(3);
		weeksTimes2.setTo(6);
		weeksTimes2.setWeeks(Arrays.asList(3,4,5));
		weektimeList.add(weeksTimes2);
		booking.setWeekTime(JSON.toJSONString(weektimeList));

		System.out.println(JSON.toJSONString(booking.getTimesWithAllWeek()));
	}

}