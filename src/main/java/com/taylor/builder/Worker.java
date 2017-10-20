package com.taylor.builder;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/19 17:19
 */
public class Worker implements IWorker {
    @Override
    public void bulidHead(CarModelTemplate carModelTemplate) {
        System.out.println(carModelTemplate.getCarName()+"：制造头部");
    }

    @Override
    public void buildTail(CarModelTemplate carModelTemplate) {
        System.out.println(carModelTemplate.getCarName()+"：制造尾部");
    }

    @Override
    public void buildWheel(CarModelTemplate carModelTemplate) {
        System.out.println(carModelTemplate.getCarName()+"：制造轮子");

    }
}
