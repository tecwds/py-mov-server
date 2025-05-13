package top.wpaint.pymov.model.dto.movie;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.wpaint.pymov.common.QueryRange;
import top.wpaint.pymov.common.RPage;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class MovieQueryDto extends RPage implements Serializable {

    @Serial
    private static final long serialVersionUID = -4490911119344133993L;

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
     * 导演列表
     */
    private List<String> directors;

    /**
     * 豆瓣评分区间查询
     */
    private QueryRange<BigDecimal> doubanScoreRange;

    /**
     * 类型
     */
    private List<String> genreNames;

    /**
     * 语言
     */
    private List<String> languages;

    /**
     * 时长区间查询
     */
    private QueryRange<Integer> minsRange;

    /**
     * 地区
     */
    private List<String> regions;

    /**
     * 上映时间区间查询
     */
    private QueryRange<Date> releaseDateRange;

    /**
     * 标签
     */
    private List<String> tags;

    /**
     * 年份区间
     */
    private QueryRange<Integer> year;
}
