package cn.yz.queue;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Description
 * @Date 2023/4/18
 * @Author yuze
 */
public class ArrayBlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {

        ArrayBlockingQueue<Integer> abq = new ArrayBlockingQueue<>(3, true);
        abq.put(1);
        abq.put(1);
        abq.put(1);
        new Thread(new Item(abq, 2)).start();
        new Thread(new Item(abq, 3)).start();
        new Thread(new Item(abq, 4)).start();
        abq.clear();
//        abq.take();
        Thread.sleep(1000);
        System.out.println(Arrays.toString(abq.toArray()));

    }

    static class Item implements Runnable {

        private ArrayBlockingQueue queue;
        private Integer value;

        public Item(ArrayBlockingQueue queue, Integer value) {
            this.queue = queue;
            this.value = value;
        }

        @Override
        public void run() {
            try {
                queue.put(value);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
