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

        String mysql_fields = "`id` char(32) NOT NULL,\n" +
            "`schedule_id` char(32) NOT NULL COMMENT '排班ID',\n" +
            "`schedule_detail_id` char(32) NOT NULL COMMENT '排班详情ID',\n" +
            "`hospital_id` char(32) NOT NULL COMMENT '医院ID',\n" +
            "`doctor_id` char(32) NOT NULL COMMENT '医生ID',\n" +
            "`doctor_name` varchar(128) NOT NULL COMMENT '医生姓名',\n" +
            "`dept_id` char(32) DEFAULT NULL COMMENT '科室ID（一级）',\n" +
            "`dept_code` varchar(16) NOT NULL COMMENT '科室编码',\n" +
            "`dept_name` varchar(128) NOT NULL COMMENT '科室名称',\n" +
            "`sub_dept_id` char(32) DEFAULT NULL COMMENT '二级科室ID',\n" +
            "`sub_dept_code` varchar(16) NOT NULL COMMENT '二级科室编码 = 门诊收费代码',\n" +
            "`sub_dept_name` varchar(128) NOT NULL COMMENT '二级科室名称',\n" +
            "`schedule_date` date NOT NULL COMMENT '号源日期',\n" +
            "`week_day` char(1) NOT NULL COMMENT '星期几(一、二、三、四、五、六、日)',\n" +
            "`outpatient_charges_code` varchar(16) DEFAULT NULL COMMENT '门诊收费代码',\n" +
            "`schedule_type` char(2) DEFAULT '1' COMMENT '门诊收费类型(号源类型)    1=普通 ,  2 = 专家,  3=专病',\n" +
            "`schedule_start_time` char(8) NOT NULL COMMENT '号源开始时间 例：10:00',\n" +
            "`schedule_end_time` char(8) NOT NULL COMMENT '号源结束时间 例：17:00',\n" +
            "`total_count` int(11) NOT NULL DEFAULT '0' COMMENT '时段号源总量',\n" +
            "`remain_count` int(11) NOT NULL DEFAULT '0' COMMENT '剩余号源数量',\n" +
            "`order_count` int(11) NOT NULL DEFAULT '0' COMMENT '已约号源数量',\n" +
            "`service_code` int(8) NOT NULL DEFAULT '0' COMMENT '10=在线复诊，20=在线咨询 ，30=核酸挂号，40=在线续方，50=远程门诊 60:全科咨询；70:预约挂号;',\n" +
            "`service_price` decimal(10,2) DEFAULT NULL COMMENT '当前排班对应的服务价格',\n" +
            "`resource_status` tinyint(4) DEFAULT '1' COMMENT '号源状态：1、正常；2.停诊',\n" +
            "`deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除1=是，0=否',\n" +
            "`is_enabled` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用1=是，0=否',\n" +
            "`is_modify` tinyint(2) DEFAULT '1' COMMENT '1、正常；2.撤销。',\n" +
            "`doctor_activity_id` int(8) DEFAULT NULL COMMENT '医生活动id',\n" +
            "`create_time` datetime NOT NULL COMMENT '创建时间',\n" +
            "`create_uid` char(32) NOT NULL COMMENT '创建用户ID',\n" +
            "`update_time` datetime NOT NULL COMMENT '更新时间',\n" +
            "`update_uid` char(32) NOT NULL COMMENT '更新用户ID',\n" +
            "`doctor_name_m` varchar(128) DEFAULT NULL COMMENT '医生姓名',";

        String dm_fields = "\"id\" VARCHAR(32) NOT NULL,\n" +
            "\"schedule_id\" VARCHAR(32) NOT NULL,\n" +
            "\"schedule_detail_id\" VARCHAR(32) NOT NULL,\n" +
            "\"hospital_id\" VARCHAR(32) NOT NULL,\n" +
            "\"doctor_id\" VARCHAR(32) NOT NULL,\n" +
            "\"doctor_name\" VARCHAR2(64) NOT NULL,\n" +
            "\"dept_id\" VARCHAR(32),\n" +
            "\"dept_code\" VARCHAR2(16) NOT NULL,\n" +
            "\"dept_name\" VARCHAR2(128) NOT NULL,\n" +
            "\"sub_dept_id\" VARCHAR(32),\n" +
            "\"sub_dept_code\" VARCHAR2(16) NOT NULL,\n" +
            "\"sub_dept_name\" VARCHAR2(128) NOT NULL,\n" +
            "\"schedule_date\" DATE NOT NULL,\n" +
            "\"week_day\" VARCHAR(1) NOT NULL,\n" +
            "\"outpatient_charges_code\" VARCHAR2(16),\n" +
            "\"schedule_type\" VARCHAR(2) DEFAULT '1',\n" +
            "\"schedule_start_time\" VARCHAR(8) NOT NULL,\n" +
            "\"schedule_end_time\" VARCHAR(8) NOT NULL,\n" +
            "\"total_count\" INT DEFAULT 0 NOT NULL,\n" +
            "\"remain_count\" INT DEFAULT 0 NOT NULL,\n" +
            "\"order_count\" INT DEFAULT 0 NOT NULL,\n" +
            "\"service_code\" INT DEFAULT 0 NOT NULL,\n" +
            "\"deleted\" TINYINT DEFAULT 0 NOT NULL,\n" +
            "\"is_enabled\" TINYINT DEFAULT 1 NOT NULL,\n" +
            "\"is_modify\" TINYINT DEFAULT 1,\n" +
            "\"create_time\" TIMESTAMP NOT NULL,\n" +
            "\"create_uid\" VARCHAR(32) NOT NULL,\n" +
            "\"update_time\" TIMESTAMP NOT NULL,\n" +
            "\"update_uid\" VARCHAR(32) NOT NULL,\n" +
            "\"service_price\" DEC,";

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
