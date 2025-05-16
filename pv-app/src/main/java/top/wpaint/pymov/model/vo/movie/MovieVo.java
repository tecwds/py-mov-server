package top.wpaint.pymov.model.vo.movie;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class MovieVo implements Serializable {
    @Serial
    private static final long serialVersionUID = -488514588953281727L;

    /**
     * 主键ID
     */
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
    private List<String> actors;

    /**
     * 封面URL
     */
    private String cover;

    /**
     * 导演列表
     */
    private List<String> directors;

    /**
     * 豆瓣评分
     */
    private BigDecimal doubanScore;

    /**
     * 豆瓣投票数
     */
    private Integer doubanVotes;

    /**
     * 类型名称
     */
    private List<String> genres;

    /**
     * IMDB ID
     */
    private String imdbId;

    /**
     * 语言
     */
    private List<String> languages;

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
    private List<String> regions;

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
}
