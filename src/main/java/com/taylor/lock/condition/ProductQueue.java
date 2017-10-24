import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProductQueue<T> {

    private final List<T> items;

    private final Lock lock = new ReentrantLock();

    private Condition notFull = lock.newCondition();

    private Condition notEmpty = lock.newCondition();

    private static final int CAPACITY = 10;

    public ProductQueue(int maxSize) {
        items = new ArrayList<>(maxSize);
    }

    public ProductQueue() {
        this(10);
    }

    public void put(T t) {
        lock.lock();
        try {
            while (!items.isEmpty()) {
                notFull.await();
            }
            for (int i = 0; i < CAPACITY; i++) {
                items.add(t);
            }
            System.out.println(Thread.currentThread().getName() + ":生产了"+CAPACITY+"个产口。。。。。。当前产品数量：" + items.size());
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T take() {
        lock.lock();
        try {
            while (items.isEmpty()) {
                notEmpty.await();
            }
            T remove = items.remove(0);
            System.out.println(Thread.currentThread().getName() + ":消费了一个商品。。。。当前产品数量：" + items.size());
            notFull.signalAll();
            return remove;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    public int size() {
        lock.lock();
        try {
            return items.size();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        ProductQueue<String> productQueue = new ProductQueue<>();

        Producer p1 = new Producer(productQueue, "p1");
        Producer p2 = new Producer(productQueue, "p2");
        Producer p3 = new Producer(productQueue, "p3");
        Consumer c1 = new Consumer(productQueue, "c1");
        Consumer c2 = new Consumer(productQueue, "c2");
        Consumer c3 = new Consumer(productQueue, "c3");

        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
        c3.start();
    }
}

class Producer extends Thread {
    private ProductQueue<String> productQueue;

    public Producer(ProductQueue<String> productQueue, String name) {
        super(name);
        this.productQueue = productQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                productQueue.put("p");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

class Consumer extends Thread {
    private ProductQueue<String> productQueue;

    public Consumer(ProductQueue<String> productQueue, String name) {
        super(name);
        this.productQueue = productQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                productQueue.take();
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}