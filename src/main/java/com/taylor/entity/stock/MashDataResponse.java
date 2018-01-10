package com.taylor.entity.stock;

import com.taylor.common.CommonResponse;
import lombok.Data;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 19:47
 */
@Data
public class MashDataResponse extends CommonResponse {
    private List<MashData> mashData;
}
