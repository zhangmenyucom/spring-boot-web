package com.taylor.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xiaolu.zhang
 * @desc: 分页插件配置
 * @date: 2017/7/13 15:52
 */
@Data
@ConfigurationProperties(prefix = PageHelperProperties.PAGEHELPER_PREFIX)
public class PageHelperProperties {
    public static final String PAGEHELPER_PREFIX = "pagehelper";
    /**
     * 3.3.0版本可用 - 分页参数合理化，默认false禁用
     * 启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页
     * 禁用合理化时，如果pageNum<1或pageNum>pages会返回空数据
     */
    private String reasonable = Boolean.TRUE.toString();

    /**
     * 支持通过Mapper接口参数来传递分页参数
     */
    private String supportMethodsArguments=Boolean.FALSE.toString();

    /**
     * always总是返回PageInfo类型,check检查返回类型是否为PageInfo,none返回Page
     */
    private String returnPageInfo = "none";

    /**
     * 3.5.0版本可用 - 为了支持startPage(Object params)方法
     * 增加了一个`params`参数来配置参数映射，用于从Map或ServletRequest中取值
     * 可以配置pageNum,pageSize,count,pageSizeZero,reasonable,orderBy,不配置映射的用默认值
     * 不理解该含义的前提下，不要随便复制该配置
     */
    private String params;

    /**
     * 4.0.0以后版本可以不设置该参数
     **/
    private String dialect = "mysql";

    /**
     * 该参数默认为false
     * 设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用
     * 和startPage中的pageNum效果一样
     **/
    private String offsetAsPageNum = Boolean.FALSE.toString();

    /**
     * 该参数默认为false
     * 设置为true时，使用RowBounds分页会进行count查询
     **/
    private String rowBoundsWithCount = Boolean.FALSE.toString();

    /**
     * 设置为true时，如果pageSize=0或者RowBounds.limit = 0就会查询出全部的结果
     * 相当于没有执行分页查询，但是返回结果仍然是Page类型
     */
    private String  pageSizeZero = Boolean.TRUE.toString();

}