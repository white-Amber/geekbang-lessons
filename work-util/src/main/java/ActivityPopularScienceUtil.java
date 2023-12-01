import org.springframework.util.CollectionUtils;
import org.springframework.util.PropertyPlaceholderHelper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author yuze
 * @date 2023/9/25
 */
public class ActivityPopularScienceUtil {

    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Class.class.getResourceAsStream("/video.txt"))));
        int count = 0;
        List<List<String>> resultList = new ArrayList<>();
        List<String> list = null;
        String line = "";
        while (!(line = reader.readLine()).equals("quit")) {
            count++;
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(line);
            if (count == 4) {
                count = 0;
                resultList.add(list);
                list = null;
            }

        }
//        System.out.println(resultList);
        Collections.reverse(resultList);
        System.out.println("size="+resultList.size());

        PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}");
        String t = "INSERT INTO internet_hospital.tb_activity_popular_science (id, channel_code, area_code, activity_name, video_title, video_picture_url, video_url, play_num, default_play_num, video_total_time, total_prize_gold_coin_num, remain_prize_gold_coin_num, single_play_prize_gold_coin_num, video_sort, deleted, create_time, update_time) VALUES ('${uuid}', '10001', '310118000000', '青浦健康答题赢金币', '${title}', '${png}', '${video}', 11085, 11085, '${time}', 0, 0, 0, ${sort}, 0, now(), now());";

        int uuidIndex = 10;
        int sortIndex = 120;
        for (int i = 0; i < resultList.size(); i++) {
            List<String> arr = resultList.get(i);
            if (!CollectionUtils.isEmpty(arr)) {
                String uuid = "751b7222e1c14002951c7e0eec1786".concat(String.valueOf(++uuidIndex));
                String sort = String.valueOf(++sortIndex);
                String title = arr.get(0);
                String video = arr.get(1);
                String png = arr.get(2);
                String time = arr.get(3);
                Map<String, String> ph = new HashMap<>();
                ph.put("uuid", uuid);
                ph.put("sort", sort);
                ph.put("title", title);
                ph.put("png", png);
                ph.put("time", time);
                ph.put("video", video);
                String sql = helper.replacePlaceholders(t, ph::get);
                System.out.println(sql);
            }
        }


    }


}
