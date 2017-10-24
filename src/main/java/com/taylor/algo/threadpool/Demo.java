package com.taylor.algo.threadpool;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/24 18:38
 */
public class Demo {

}

@Data
class AddThread implements Callable<Integer> {
    private int start;
    private int end;

    public AddThread(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (; start <= end; start++) {
            sum += start;
        }
        return sum;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            resultList.add(executorService.submit(new AddThread(i * 10 + 1, 10 + i * 10)));
        }
        executorService.shutdown();
        // 遍历任务的结果
        int all = 0;
        for (Future<Integer> fs : resultList) {
            try {
                all = all + fs.get(); // 打印各个线程（任务）执行的结果
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }
        System.out.println(all);
    }
}
