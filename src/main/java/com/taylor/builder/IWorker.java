package com.taylor.builder;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/19 17:15
 */
public interface IWorker {
    void bulidHead(CarModelTemplate carModelTemplate);

    void buildTail(CarModelTemplate carModelTemplate);

    void buildWheel(CarModelTemplate carModelTemplate);
}
