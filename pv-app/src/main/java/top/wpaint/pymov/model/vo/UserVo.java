package top.wpaint.pymov.model.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 3466451110328326517L;

    /**
     * 豆瓣用户ID
     */
    private String user_id;

    /**
     * 用户昵称
     */
    private String user_nickname;

    /**
     * 用户账号
     */
    private String user_account;

}
