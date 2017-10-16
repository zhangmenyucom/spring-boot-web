import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 同步线程
 */

@Data
@AllArgsConstructor
public class SyncThread implements Runnable {

    private int a;

    public synchronized void del() {
        a++;
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + "--del");
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public  synchronized void add() {
        a--;
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + "--add");
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        if (a == 1) {
            add();
        } else {
            del();
        }
    }


    public static void main(String... args) {
        SyncThread syncThread = new SyncThread(1);
        Thread thread1 = new Thread(syncThread, "SyncThread1");
        Thread thread2 = new Thread(syncThread, "SyncThread2");
        thread1.start();
        thread2.start();

    }
}