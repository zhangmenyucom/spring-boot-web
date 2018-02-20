package com.taylor.entity.stock;

import com.taylor.common.CommonRequest;
import lombok.Data;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/30 14:26
 */
@Data
public class AskCaiReqBean {
    private Integer pid = 8093;
    private String codes = "002839";
    private String codeType = "stock";
    private String info = "{\"view\":{\"nolazy\":1,\"parseArr\":{\"_v\":\"new\",\"dateRange\":[],\"staying\":[],\"queryCompare\":[],\"comparesOfIndex\":[]},\"asyncParams\":{\"tid\":137}}}";

    public static void main(String... args) {
        System.out.println(new CommonRequest<>().executeRequest(new AskCaiReqBean(),new PostMethod("http://www.iwencai.com/diag/block-detail")));

    }
}
