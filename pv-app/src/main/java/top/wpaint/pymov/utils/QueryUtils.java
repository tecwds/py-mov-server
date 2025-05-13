package top.wpaint.pymov.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import top.wpaint.pymov.common.QueryRange;
import top.wpaint.pymov.model.entity.Movie;

import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QueryUtils {

    /**
     * 列表查询条件的 QueryWrapper 辅助构造方法
     * @param query QueryWrapper
     * @param list 需要查询的列表
     * @param getColumn 需要查询的列名获取方法
     * @param <E> 实体类类型
     * @param <T> 列名类型
     */
    public static <E, T> void queryListHelper(LambdaQueryWrapper<E> query, List<T> list, SFunction<E, T> getColumn) {
        if (CollUtil.isNotEmpty(list)) {
            query.and(q -> {
                list.forEach(item -> {
                    q.or().like(Objects.nonNull(item), getColumn, item);
                });
            });
        }
    }

    /**
     * 查询范围查询条件的 QueryWrapper 辅助构造方法
     * @param query QueryWrapper
     * @param range 查询范围
     * @param getColumn 需要查询的列名获取方法
     * @param <E> 实体类类型
     * @param <T> 列名类型
     */
    public static <E, T> void queryRangeHelper(LambdaQueryWrapper<E> query, QueryRange<T> range, SFunction<E, T> getColumn) {
        // range 不为 null 并且至少存在一个 min 或 max 不为空
        if (range != null && !ObjUtil.isAllEmpty(range.getMin(), range.getMax())) {
            query.and(q -> {
                // 大于最小
                q.ge(ObjUtil.isNotEmpty(range.getMin()),  getColumn, range.getMin());

                // 小于最大
                q.le(ObjUtil.isNotEmpty(range.getMax()),  getColumn, range.getMax());
            });
        }
    }
}
