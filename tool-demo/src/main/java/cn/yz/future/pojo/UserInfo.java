package cn.yz.future.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Date 2023/4/18
 * @Author yuze
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private String id;
    private String name;
    private Integer age;
}
