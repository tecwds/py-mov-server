package top.wpaint.pymov.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName pv_movie
 */
@TableName(value ="pv_movie")
@Data
public class Movie implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 电影ID
     */
    private String movieId;

    /**
     * 电影名称
     */
    private String name;

    /**
     * 别名
     */
    private String alias;

    /**
     * 演员列表
     */
    private String actors;

    /**
     * 封面URL
     */
    private String cover;

    /**
     * 导演列表
     */
    private String directors;

    /**
     * 豆瓣评分
     */
    private BigDecimal doubanScore;

    /**
     * 豆瓣投票数
     */
    private Integer doubanVotes;

    /**
     * 类型
     */
    private String genres;

    /**
     * IMDB ID
     */
    private String imdbId;

    /**
     * 语言
     */
    private String languages;

    /**
     * 时长(分钟)
     */
    private Integer mins;

    /**
     * 官方网站
     */
    private String officialSite;

    /**
     * 地区
     */
    private String regions;

    /**
     * 上映日期
     */
    private Date releaseDate;

    /**
     * 短链标识
     */
    private String slug;

    /**
     * 剧情简介
     */
    private String storyline;

    /**
     * 标签
     */
    private String tags;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 演员ID列表
     */
    private String actorIds;

    /**
     * 导演ID列表
     */
    private String directorIds;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}