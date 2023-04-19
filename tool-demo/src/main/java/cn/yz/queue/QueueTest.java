package cn.yz.queue;

import cn.yz.queue.pojo.User;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Description
 * @Date 2023/4/18
 * @Author yuze
 */
public class QueueTest {

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(5);
        pq.offer(1);
        pq.offer(1);
        pq.remove(1);
        pq.remove(1);
        System.out.println(pq.peek());

        PriorityQueue<User> queueUser = new PriorityQueue<>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getAge().equals(o2.getAge()) ? 0 : o1.getAge() > o2.getAge() ? 1 : -1;
            }
        });

        User user1 = new User("zhangSan", 18);
        User user2 = new User("xiaoMei", 18);
        User user3 = new User("pangHu", 18);
        User user4 = new User("liSi", 44);
        queueUser.offer(user3);
        queueUser.offer(user1);
        queueUser.offer(user2);
        queueUser.offer(user4);
        System.out.println(queueUser.peek());

        ConcurrentLinkedQueue<Integer> linkedQueue = new ConcurrentLinkedQueue<>();
        linkedQueue.offer(1);
        linkedQueue.offer(1);
        linkedQueue.offer(2);
        linkedQueue.remove(1);
        System.out.println(linkedQueue.peek());

        ArrayBlockingQueue<Integer> abq = new ArrayBlockingQueue<>(1);
        System.out.println(abq.poll());


    }




}
