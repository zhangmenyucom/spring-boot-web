package com.taylor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Created by fujin.zhang on 17/2/15.
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Data
public class BookingWeeksTimes {
    private List<Integer> weeks;
    private int from;
    private int to;
}
