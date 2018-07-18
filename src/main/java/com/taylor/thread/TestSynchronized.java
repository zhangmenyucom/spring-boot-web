public class TestSynchronized {
    public void test1() {
        synchronized (this) {
            int i = 5;
            while (i > 0) {
                i--;
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                }
            }
            i--;
        }
    }

    public synchronized void test2() {
        int i = 5;
        while (i > 0) {
            i--;
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {
            }
        }
        i--;
    }

    public static void main(String... args) {
        final TestSynchronized myt2 = new TestSynchronized();
        Thread test1 = new Thread(new Runnable() {
            @Override
            public void run() {
                myt2.test1();
            }
        }, "test1");
        Thread test2 = new Thread(new Runnable() {
            @Override
            public void run() {
                myt2.test2();
            }
        }, "test2");
        test1.start();
        test2.start();
    }

}