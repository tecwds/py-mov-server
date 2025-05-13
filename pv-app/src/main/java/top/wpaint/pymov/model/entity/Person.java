package top.wpaint.pymov.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName pv_person
 */
@TableName(value ="pv_person")
@Data
public class Person implements Serializable {
    /**
     * 名人ID
     */
    @TableId
    private Long personId;

    /**
     * 演员名称
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 更多英文名
     */
    private String nameEn;

    /**
     * 更多中文名
     */
    private String nameZh;

    /**
     * 出生年份
     */
    private Integer birthYear;

    /**
     * 出生月份
     */
    private Integer birthMonth;

    /**
     * 出生日期
     */
    private Integer birthDay;

    /**
     * 出生地
     */
    private String birthplace;

    /**
     * 星座
     */
    private String constellatory;

    /**
     * 职业
     */
    private String profession;

    /**
     * 简介，存在简介数据的名人只有15135个
     */
    private String biography;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}