package cn.yz.date;


/**
 * @Description
 * @Date 2023/6/13
 * @Author yuze
 */
public class Demo {

    public static void main(String[] args) {
        long time = DateUtil.betweenWeek(DateUtil.parseDateTime("2023-06-11 00:00:01"), DateUtil.parseDateTime("2023-06-18 00:00:00"), true);
        System.out.println(time);


    }

}
