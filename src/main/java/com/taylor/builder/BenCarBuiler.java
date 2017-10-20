package com.taylor.builder;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/19 17:14
 */
public class BenCarBuiler {

    private IWorker iworker;

    BenCarBuiler(){
        iworker=new Worker();
    }

    public BenzCar build() {
        BenzCar benzCar = new BenzCar();
        benzCar.setCarName("奔驰车");
        iworker.buildTail(benzCar);
        iworker.buildWheel(benzCar);
        iworker.bulidHead(benzCar);
        return benzCar;
    }

}
