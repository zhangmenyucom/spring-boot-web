package com.taylor.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessHoursDto {

	/*timeframe:{"title":"33333","oldTitle":"","timeframeList":[{"startTime":"00:00","endTime":"00:00","SUM":true,
		"MON":false,"TUE":false,"WED":false,"THU":false,"FIR":false,"SAT":false,"key":0,"$$hashKey":"004"},
		{"startTime":"00:00","endTime":"00:00","SUM":true,"MON":true,"TUE":true,"WED":false,"THU":false,"FIR":false,
			"SAT":false,"key":1,"$$hashKey":"006"}]}*/
	private String title;
	private String oldTitle;
	private List<TimeframeVo> timeframeList;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class TimeframeVo {
		private String startTime;
		private String endTime;
		
		@JsonProperty("SUM")
		private Boolean sunday = false;
		@JsonProperty("MON")
		private Boolean monday = false;
		@JsonProperty("TUE")
		private Boolean tuesday = false;
		@JsonProperty("WED")
		private Boolean wednesday = false;
		@JsonProperty("THU")
		private Boolean thursday = false;
		@JsonProperty("FIR")
		private Boolean friday = false;
		@JsonProperty("SAT")
		private Boolean saturday = false;
		
	}
}
