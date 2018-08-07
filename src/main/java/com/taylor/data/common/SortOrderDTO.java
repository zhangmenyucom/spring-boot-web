package com.taylor.data.common;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

/**
 * @author jearton
 * @since 2018/1/24 下午9:54
 */
@Data
@RequiredArgsConstructor
public class SortOrderDTO {

    private final String property;

    private final Sort.Direction direction;

    public SortOrderDTO(Order order) {
        this.direction = order.getDirection();
        this.property = order.getProperty();
    }
}
