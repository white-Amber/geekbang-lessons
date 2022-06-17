import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
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
                "  `hospital_id` char(32) NOT NULL COMMENT '医院ID',\n" +
                "  `hospital_code` char(32) NOT NULL COMMENT '医院编码',\n" +
                "  `drug_code` varchar(16) DEFAULT NULL COMMENT '药品编码',\n" +
                "  `hospital_drug_code` varchar(24) DEFAULT NULL COMMENT '医院本地药品代码',\n" +
                "  `name` varchar(128) DEFAULT NULL COMMENT '药品名称',\n" +
                "  `name_simplify_pinying` varchar(50) DEFAULT NULL COMMENT '药品名称首字母拼音',\n" +
                "  `general_name` varchar(255) DEFAULT NULL COMMENT '药品通用名',\n" +
                "  `specs` varchar(64) DEFAULT NULL COMMENT '规格',\n" +
                "  `price` decimal(10,4) DEFAULT NULL COMMENT '价格',\n" +
                "  `dose_unit` varchar(64) DEFAULT NULL COMMENT '剂量单位',\n" +
                "  `dose` varchar(64) DEFAULT NULL COMMENT '剂量',\n" +
                "  `dosage_type` varchar(64) DEFAULT NULL COMMENT '剂型',\n" +
                "  `dosage_unit` varchar(45) DEFAULT NULL COMMENT '剂型单位',\n" +
                "  `unit` varchar(16) DEFAULT NULL COMMENT '包装单位',\n" +
                "  `packing_number` varchar(25) DEFAULT NULL COMMENT '包装数量, 比如’200mg*7片/盒’ 中的7',\n" +
                "  `source_code` varchar(10) DEFAULT '10' COMMENT '药品配送来源：10=上药，20=国药，30=医院自配，40=国新，60=药易',\n" +
                "  `stock_source_code` tinyint(4) DEFAULT NULL COMMENT '药品库存来源：10=上药，20=国药，30=医院本地药房(his)，40=国新',\n" +
                "  `drug_type` char(1) DEFAULT NULL COMMENT '药品类型: 西药=X、中成药=Z 、中药 =Y 、自制剂=J 、中药代煎费=S',\n" +
                "  `medicare_attribute` varchar(45) DEFAULT NULL COMMENT '医保属性: 甲类, 乙类',\n" +
                "  `self_supporting_ratio` varchar(45) DEFAULT NULL COMMENT '自费比例: 乙类:0, 甲类:10%',\n" +
                "  `reimbursement_ratio` varchar(45) DEFAULT NULL COMMENT '医保报销比例',\n" +
                "  `national_drug_approval_number` varchar(45) DEFAULT NULL COMMENT '国药准字号',\n" +
                "  `reimbursement_self_supporting_type` varchar(15) DEFAULT NULL COMMENT '医保自理类型',\n" +
                "  `supplier` varchar(255) DEFAULT NULL COMMENT '供应商',\n" +
                "  `medicine_manufacturer` varchar(255) DEFAULT NULL COMMENT '生产厂家',\n" +
                "  `indication` varchar(255) DEFAULT NULL COMMENT '适应症',\n" +
                "  `supplier_drug_code` varchar(16) DEFAULT NULL COMMENT '供应商商品编码',\n" +
                "  `description` varchar(255) DEFAULT NULL COMMENT '药品描述',\n" +
                "  `drug_code_sku` varchar(32) DEFAULT NULL COMMENT '药品编码SKU',\n" +
                "  `retail_code` varchar(16) DEFAULT NULL COMMENT '商品分销代码',\n" +
                "  `drugs_prescription_type` tinyint(4) DEFAULT '10' COMMENT '处方类型10=普通处方，20=延伸处方，30=长处方',\n" +
                "  `is_stock` tinyint(4) DEFAULT '1' COMMENT '是否有库存: 0=否，1=是',\n" +
                "  `guoyao_stock` tinyint(4) DEFAULT '1' COMMENT '国药库存  0=没有库存, 1-有库存;',\n" +
                "  `shangyao_stock` tinyint(4) DEFAULT '1' COMMENT '上药库存  0=没有库存,1-有库存;',\n" +
                "  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否可用1=是，0=否',\n" +
                "  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除1=是，0=否',\n" +
                "  `create_time` datetime NOT NULL COMMENT '创建时间',\n" +
                "  `create_uid` varchar(64) NOT NULL COMMENT '创建用户ID',\n" +
                "  `update_time` datetime NOT NULL COMMENT '更新时间',\n" +
                "  `update_uid` varchar(64) NOT NULL COMMENT '更新用户ID',\n" +
                "  `is_long_prescription` tinyint(4) DEFAULT '0' COMMENT '是否长处方药品1=是，0=否',\n" +
                "  `is_extend_prescription` tinyint(4) DEFAULT '0' COMMENT '是否延伸处方药品1=是，0=否',\n" +
                "  `is_hospital_preparation` tinyint(4) DEFAULT '0' COMMENT '院内制剂标志\\n0、非自制药品；\\r1、自制药品。',\n" +
                "  `is_base_durg` tinyint(4) DEFAULT '0' COMMENT '1、国基药；\\r2、上海增补基药；\\r0、非前述两种',\n" +
                "  `is_antibiotic` tinyint(4) DEFAULT '0' COMMENT '抗生素标识 1、抗生素； 0、非抗生素。',\n" +
                "  `is_measles_toxin` tinyint(4) DEFAULT '0' COMMENT '毒麻精放标识: 1、是；0、否；',\n" +
                "  `dosage_form_code` varchar(10) DEFAULT NULL COMMENT '剂型代码',\n" +
                "  `drug_route_code` varchar(50) DEFAULT NULL COMMENT '给药途径code',\n" +
                "  `drug_route` varchar(50) DEFAULT NULL COMMENT '给药途径',";

        String dm_fields = "\"id\" VARCHAR(32) NOT NULL,\n" +
                "\t\"hospital_id\" VARCHAR(32) NOT NULL,\n" +
                "\t\"hospital_code\" VARCHAR(32) NOT NULL,\n" +
                "\t\"drug_code\" VARCHAR2(16),\n" +
                "\t\"hospital_drug_code\" VARCHAR2(24),\n" +
                "\t\"drug_type\" VARCHAR(1),\n" +
                "\t\"drug_code_sku\" VARCHAR2(32),\n" +
                "\t\"name\" VARCHAR2(128),\n" +
                "\t\"general_name\" VARCHAR2(255),\n" +
                "\t\"dose_unit\" VARCHAR2(64),\n" +
                "\t\"dose\" VARCHAR2(64),\n" +
                "\t\"dosage_type\" VARCHAR2(64),\n" +
                "\t\"dosage_unit\" VARCHAR2(45),\n" +
                "\t\"supplier\" VARCHAR2(255),\n" +
                "\t\"medicine_manufacturer\" VARCHAR2(255),\n" +
                "\t\"specs\" VARCHAR2(64),\n" +
                "\t\"description\" VARCHAR2(255),\n" +
                "\t\"unit\" VARCHAR2(16),\n" +
                "\t\"price\" DEC,\n" +
                "\t\"indication\" VARCHAR2(255),\n" +
                "\t\"medicare_attribute\" VARCHAR2(45),\n" +
                "\t\"self_supporting_ratio\" VARCHAR2(45),\n" +
                "\t\"reimbursement_ratio\" VARCHAR2(45),\n" +
                "\t\"national_drug_approval_number\" VARCHAR2(45),\n" +
                "\t\"reimbursement_self_supporting_type\" VARCHAR2(15),\n" +
                "\t\"retail_code\" VARCHAR2(16),\n" +
                "\t\"source_code\" TINYINT DEFAULT 10,\n" +
                "\t\"drugs_prescription_type\" TINYINT DEFAULT 10,\n" +
                "\t\"supplier_drug_code\" VARCHAR2(16),\n" +
                "\t\"is_stock\" TINYINT DEFAULT 1,\n" +
                "\t\"enabled\" TINYINT DEFAULT 1 NOT NULL,\n" +
                "\t\"deleted\" TINYINT DEFAULT 0 NOT NULL,\n" +
                "\t\"create_time\" TIMESTAMP NOT NULL,\n" +
                "\t\"create_uid\" VARCHAR2(64) NOT NULL,\n" +
                "\t\"update_time\" TIMESTAMP NOT NULL,\n" +
                "\t\"update_uid\" VARCHAR2(64) NOT NULL,\n" +
                "\t\"packing_number\" VARCHAR2(25),\n" +
                "\t\"is_long_prescription\" TINYINT DEFAULT 0,\n" +
                "\t\"is_extend_prescription\" TINYINT DEFAULT 0,\n" +
                "\t\"is_hospital_preparation\" TINYINT DEFAULT 0,\n" +
                "\t\"is_base_durg\" TINYINT DEFAULT 0,\n" +
                "\t\"is_antibiotic\" TINYINT DEFAULT 0,\n" +
                "\t\"is_measles_toxin\" TINYINT DEFAULT 0,\n" +
                "\t\"dosage_form_code\" VARCHAR2(10),\n" +
                "\t\"drug_route_code\" VARCHAR2(50),\n" +
                "\t\"drug_route\" VARCHAR2(50),\n" +
                "\t\"stock_source_code\" TINYINT,\n" +
                "\t\"name_simplify_pinying\" VARCHAR(50),";

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
