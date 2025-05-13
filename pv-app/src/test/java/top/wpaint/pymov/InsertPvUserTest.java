package top.wpaint.pymov;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
        List<String> userIds = userService.list(new Page<>(1, 200)).stream().map(User::getUserId).toList();

        List<UserLike> userLikes = new ArrayList<>(2000);

        Map<String, List<Long>> userIdAndGenreIdsMap = new HashMap<>();

        for (String userId : userIds) {
            Set<Long> genreIdsSet = new HashSet<>();

            // 生成 5 个 genreId
            for (int i = 0; i < 5; i++) {
                Collections.shuffle(genreIds);
                genreIdsSet.add(genreIds.get(new Random().nextInt(genreIds.size())));
            }

            userIdAndGenreIdsMap.put(userId, genreIdsSet.stream().toList());
        }

        userIdAndGenreIdsMap.forEach((userId, gIds) -> {
            gIds.forEach(item -> {
                UserLike ul = new UserLike();
                ul.setUserId(userId);
                ul.setGenreId(item);
                userLikes.add(ul);
            });
        });

        userLikeService.saveBatch(userLikes);
    }
}
