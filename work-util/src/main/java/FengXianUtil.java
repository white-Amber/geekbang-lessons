import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Date 2022/6/15
 * @Author yuze
 */
public class FengXianUtil {

    public static void main(String[] args) {

        filterNewAddField();

    }

    /**
     * 比较mysql和达梦表字段数量的差异
     */
    public static void filterNewAddField() {

        String mysql_fields = "`id` varchar(32) NOT NULL,\n" +
            "`username` varchar(32) NOT NULL COMMENT '用户名',\n" +
            "`password` varchar(32) NOT NULL COMMENT '密码',\n" +
            "`name` varchar(32) DEFAULT NULL COMMENT '姓名',\n" +
            "`name_e` varchar(192) DEFAULT NULL,\n" +
            "`name_m` varchar(64) DEFAULT NULL,\n" +
            "`belong_branch_code` bigint(18) NOT NULL COMMENT '所属机构Id',\n" +
            "`employee_no` varchar(16) DEFAULT NULL COMMENT '工号',\n" +
            "`sex` tinyint(1) NOT NULL COMMENT 'GB/T 2261.1-2003性别代码：0=未知的性别；1=男性；2=女性； 9=未说明的性别',\n" +
            "`birthday` char(10) DEFAULT NULL COMMENT '生日 2018-04-23',\n" +
            "`email` varchar(64) DEFAULT NULL COMMENT '邮箱',\n" +
            "`email_e` varchar(192) DEFAULT NULL,\n" +
            "`email_m` varchar(64) DEFAULT NULL,\n" +
            "`status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态：0=未启用状态；1=正常状态',\n" +
            "`mobile` varchar(32) DEFAULT NULL COMMENT '手机号',\n" +
            "`mobile_e` varchar(192) DEFAULT NULL,\n" +
            "`mobile_m` varchar(64) DEFAULT NULL,\n" +
            "`description` varchar(1024) DEFAULT NULL COMMENT '描述',\n" +
            "`deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除： 0=未删除；1=删除',\n" +
            "`role_id` varchar(100) DEFAULT '0' COMMENT '角色id',\n" +
            "`create_time` datetime NOT NULL COMMENT '创建时间',\n" +
            "`create_uid` varchar(32) NOT NULL COMMENT '创建用户ID',\n" +
            "`edit_time` datetime DEFAULT NULL COMMENT '更新时间',\n" +
            "`edit_uid` varchar(32) DEFAULT NULL COMMENT '更新用户ID',\n" +
            "`login_fail_times` int(2) DEFAULT NULL,\n" +
            "`locked_time` datetime DEFAULT NULL,\n" +
            "`lastest_login_time` datetime DEFAULT NULL COMMENT '最近一次登录时间',\n" +
            "`login_times` int(11) DEFAULT '0' COMMENT '登录系统次数',\n" +
            "`is_locked` tinyint(1) DEFAULT '0',\n" +
            "`reset_password` tinyint(1) DEFAULT '1' COMMENT '是否需要重置密码：1=是，0=否',\n" +
            "`first_set_password_time` datetime DEFAULT NULL COMMENT '首次设置密码时间',\n" +
            "`set_email_time` datetime DEFAULT NULL COMMENT '设置邮箱时间',\n" +
            "`email_verify_time` datetime DEFAULT NULL COMMENT '邮箱认证时间',\n" +
            "`is_verify_email` tinyint(4) DEFAULT '0' COMMENT '邮箱是否验证（0、未验证   1、已验证）',\n" +
            "`set_mobile_time` datetime DEFAULT NULL COMMENT '设置手机号码时间',\n" +
            "`is_verify_mobile` tinyint(4) DEFAULT NULL COMMENT '手机是否验证（0、未验证   1、已验证）',\n" +
            "`mobile_verify_time` datetime DEFAULT NULL COMMENT '手机号认证时间',\n" +
            "`last_set_password_time` datetime DEFAULT NULL COMMENT '上次更新密码时间',\n" +
            "`sensitive_data_password` varchar(32) DEFAULT NULL COMMENT '敏感信息密码',";

        String dm_fields = "\"id\" VARCHAR2(32) NOT NULL,\n" +
            "\"username\" VARCHAR2(32) NOT NULL,\n" +
            "\"password\" VARCHAR2(32) NOT NULL,\n" +
            "\"name\" VARCHAR2(32) NOT NULL,\n" +
            "\"belong_branch_code\" BIGINT NOT NULL,\n" +
            "\"employee_no\" VARCHAR2(16),\n" +
            "\"sex\" TINYINT NOT NULL,\n" +
            "\"birthday\" VARCHAR(10),\n" +
            "\"email\" VARCHAR2(64),\n" +
            "\"status\" TINYINT DEFAULT 1 NOT NULL,\n" +
            "\"mobile\" VARCHAR2(32),\n" +
            "\"description\" VARCHAR2(1024),\n" +
            "\"deleted\" TINYINT DEFAULT 0,\n" +
            "\"role_id\" VARCHAR2(100) DEFAULT '0',\n" +
            "\"create_time\" TIMESTAMP NOT NULL,\n" +
            "\"create_uid\" VARCHAR2(32) NOT NULL,\n" +
            "\"edit_time\" TIMESTAMP,\n" +
            "\"edit_uid\" VARCHAR2(32),\n" +
            "\"login_fail_times\" INT,\n" +
            "\"locked_time\" TIMESTAMP,\n" +
            "\"lastest_login_time\" TIMESTAMP,\n" +
            "\"login_times\" INT DEFAULT 0,\n" +
            "\"is_locked\" TINYINT DEFAULT 0,\n" +
            "\"reset_password\" TINYINT DEFAULT 1,\n" +
            "\"first_set_password_time\" TIMESTAMP,\n" +
            "\"set_email_time\" TIMESTAMP,\n" +
            "\"email_verify_time\" TIMESTAMP,\n" +
            "\"is_verify_email\" TINYINT DEFAULT 0,\n" +
            "\"set_mobile_time\" TIMESTAMP,\n" +
            "\"is_verify_mobile\" TINYINT,\n" +
            "\"mobile_verify_time\" TIMESTAMP,\n" +
            "\"last_set_password_time\" TIMESTAMP,\n" +
            "\"sensitive_data_password\" TIMESTAMP,";

        List<String> mysqlFieldList = new ArrayList<>();
        List<String> dmFieldList = new ArrayList<>();

        String[] mysqlSplit = mysql_fields.split("\n");
        for (String s : mysqlSplit) {
            String field = s.substring(s.indexOf("`")+1, s.lastIndexOf("`"));
            mysqlFieldList.add(field);
        }

        String[] dmSplit = dm_fields.split("\n");
        for (String s : dmSplit) {
            String field = s.substring(s.indexOf("\"")+1, s.lastIndexOf("\""));
            dmFieldList.add(field);
        }

        mysqlFieldList.removeAll(dmFieldList);

        System.out.println(mysqlFieldList);

    }


}
