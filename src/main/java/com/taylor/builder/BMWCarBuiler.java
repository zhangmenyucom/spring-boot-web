package com.taylor.builder;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/19 17:14
 */
public class BMWCarBuiler {

    private IWorker iworker;

    BMWCarBuiler(){
        iworker=new Worker();
    }

    public BWMCar build() {
        BWMCar bwmCar = new BWMCar();
        bwmCar.setCarName("宝马");
        iworker.buildWheel(bwmCar);
        iworker.buildTail(bwmCar);
        iworker.bulidHead(bwmCar);
        return bwmCar;
    }

}
