package top.wpaint.pymov;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.wpaint.pymov.model.entity.Genre;
import top.wpaint.pymov.model.entity.User;
import top.wpaint.pymov.model.entity.UserLike;
import top.wpaint.pymov.service.GenreService;
import top.wpaint.pymov.service.UserLikeService;
import top.wpaint.pymov.service.UserService;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@SpringBootTest
public class InsertPvUserTest {

    @Resource
    private UserService userService;

    @Resource
    private UserLikeService userLikeService;

    @Resource
    private GenreService genreService;

    @Test
    void insert() {
        List<Long> genreIds = new ArrayList<>(genreService.list().stream().map(Genre::getId).toList());
        List<String> userIds = userService.list(new Page<>(1, 400)).stream().map(User::getUserId).toList();

        List<UserLike> userLikes = new ArrayList<>(2000);

        Map<String, List<Long>> userIdAndGenreIdsMap = new HashMap<>();

        int max = 400;
        int count = 1;
        for (String userId : userIds) {
            Set<Long> genreIdsSet = new HashSet<>();

            // 生成 5 个 genreId
            for (int i = 0; i < 5; i++) {
                Collections.shuffle(genreIds);
                genreIdsSet.add(genreIds.get(new Random().nextInt(genreIds.size())));
            }

            userIdAndGenreIdsMap.put(userId, genreIdsSet.stream().toList());

            // 这里打印进度
            if (count % 10 == 0) {
                log.info("进度：{} / {}", count, max);
            }

            count++;
        }

        log.info("开始转换数据");
        userIdAndGenreIdsMap.forEach((userId, gIds) -> {
            gIds.forEach(item -> {
                UserLike ul = new UserLike();
                ul.setUserId(userId);
                ul.setGenreId(item);
                userLikes.add(ul);
            });
        });
        log.info("转换数据结束");

        long start = System.currentTimeMillis();
        userLikeService.saveBatch(userLikes, 500);
        long end = System.currentTimeMillis();

        log.info("执行保存 SQL 花费了: {}s", (end - start) / 1000);
    }
}
