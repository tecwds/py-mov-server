package top.wpaint.pymov.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswdUtil {

    /**
     * 加密密码
     *
     * @param password 原始密码
     * @return 加密后密码
     */
    public static String encryptPasswd(String password) {
        return DigestUtils.md5DigestAsHex(("pymov" + password).getBytes(StandardCharsets.UTF_8));
    }
}
