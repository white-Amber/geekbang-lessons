import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description
 * @Date 2022/11/22
 * @Author yuze
 */
public class CreateTableUtil {

    public static void main(String[] args) {

        String tableName = "\"internet_hospital\".\"sys_user_verification_code\"";

        String sql = "CREATE TABLE `sys_user_verification_code`\n" +
            "(\n" +
            "    `id`                varchar(32)         NOT NULL,\n" +
            "    `user_id`           char(36)     DEFAULT NULL COMMENT '用户id',\n" +
            "    `type`              varchar(50)         NOT NULL COMMENT '验证码类型：login=登录,register=注册,forget_password=找回密码,change_mobile=修改手机号,verify_mobile=手机号认证,verify_email=邮箱认证',\n" +
            "    `mobile`            varchar(128) DEFAULT NULL COMMENT '手机号',\n" +
            "    `mobile_e`          varchar(192) DEFAULT NULL,\n" +
            "    `mobile_m`          varchar(128) DEFAULT NULL,\n" +
            "    `email`             varchar(128) DEFAULT NULL COMMENT '邮箱',\n" +
            "    `email_e`           varchar(192) DEFAULT NULL,\n" +
            "    `email_m`           varchar(128) DEFAULT NULL,\n" +
            "    `verification_code` varchar(6)          NOT NULL COMMENT '短信验证码',\n" +
            "    `expired_time`      bigint(20) unsigned NOT NULL COMMENT '过期时间',\n" +
            "    `valid`             tinyint(1) unsigned NOT NULL COMMENT '是否有效：1是，0否',\n" +
            "    `create_time`       datetime            NOT NULL,\n" +
            "    `update_time`       datetime     DEFAULT NULL COMMENT '更新时间',\n" +
            "    `sms_id`            char(36)     DEFAULT NULL,\n" +
            "    PRIMARY KEY (`id`) USING BTREE,\n" +
            "    KEY `type` (`type`) USING BTREE,\n" +
            "    KEY `mobile` (`mobile`) USING BTREE,\n" +
            "    KEY `valid` (`valid`) USING BTREE,\n" +
            "    KEY `create_time` (`create_time`) USING BTREE\n" +
            ") ENGINE = InnoDB\n" +
            "  DEFAULT CHARSET = utf8\n" +
            "  ROW_FORMAT = COMPACT COMMENT ='短信验证码表';";

        System.out.println(convertToDm(sql, tableName));


    }

    public static String convertToDm(String mysqlCreateTable, String tableName) {
        StringBuilder sb = new StringBuilder();
        Map<String, String> indexMap = new LinkedHashMap<>();
        Map<String, String> commentMap = new LinkedHashMap<>();
        String[] split = mysqlCreateTable.split("\n");
        for (String line : split) {
            if (line.startsWith("CREATE TABLE")) {
                sb.append("CREATE TABLE ").append(tableName).append("\n");
                sb.append("(").append("\n");
            } else if (line.startsWith("    `")) {
                // 字段类型替换
                line = replaceFieldType(line);
                // 主键处理
                if (line.startsWith("    `id`")) {
                    String replaceLine = line.replaceAll("`", "").replace(",", " primary key, ");
                    sb.append(replaceLine).append("\n");
                } else {
                    // 非主键处理
                    String fieldName = line.substring(line.indexOf("`")+1, line.indexOf("` "));
                    String replaceAllLine = line.replaceAll("`", "");
                    if (replaceAllLine.contains("COMMENT")) {
                        String substring = replaceAllLine.substring(0, replaceAllLine.indexOf("COMMENT")).concat(", ").concat("\n");
                        sb.append(substring);
                        // 注释内容收集
                        String commentStr  = replaceAllLine.substring(replaceAllLine.indexOf("COMMENT"));
                        commentMap.put(fieldName, commentStr);
                    } else {
                        sb.append(replaceAllLine).append("\n");
                    }
                }
            } else if (line.startsWith("PRIMARY KEY (")) {
                // do nothing.
            } else if (line.startsWith("    KEY `")) {
                String indexName = line.substring(line.indexOf(" `")+2, line.indexOf("` "));
                String index_field = line.substring(line.indexOf("(`")+2, line.indexOf("`)"));
                indexMap.put(indexName, index_field);
            } else if (line.contains("COMMENT =")) {
                String tableComment = line.substring(line.indexOf(" '")+2, line.indexOf("';"));
                commentMap.put("tableComment", tableComment);
            } else {
                // do nothing.
            }
        }
        sb.append(")").append("\n");
        // 处理字段注释
        for (String key : commentMap.keySet()) {
            String tableCommentTemplate = "comment on table %s is '%s';";
            String fieldCommentTemplate = "comment on column %s.\"%s\" is '%s';";
            if (key.equals("tableComment")) {
                sb.append(String.format(tableCommentTemplate, tableName, commentMap.get(key))).append("\n");
            } else {
                String commentStr = commentMap.get(key);
                String commentResult = commentStr.substring(commentStr.indexOf(" '") + 2, commentStr.indexOf("',"));
                sb.append(String.format(fieldCommentTemplate, tableName, key, commentResult)).append("\n");
            }
        }
        sb.append("\n");
        // 处理索引
        for (String key : indexMap.keySet()) {
            String indexTemplate = "create index %s\n" +
                "    on %s (%s);";
            sb.append(String.format(indexTemplate, key, tableName, indexMap.get(key))).append("\n");
        }

        return sb.toString();
    }

    private static String replaceFieldType(String line) {
        return line.replaceAll(" char\\(", " varchar(")
            .replaceAll("int\\([0-9]{0,3}\\)", "int")
            .replaceAll("tinyint\\([0-9]{0,3}\\)", "tinyint")
            .replaceAll("timestamp", "TIMESTAMP(0)")
            .replaceAll("datetime", "TIMESTAMP(0)")
            .replaceAll("bigint\\([0-9]{0,3}\\)", "bigint")
            .replaceAll("decimal\\(", "dec(");
    }


}
