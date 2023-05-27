package cuculi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cuculi.common.BaseContext;
import cuculi.common.R;
import cuculi.pojo.Friends;
import cuculi.pojo.Users;
import cuculi.service.FriendsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/friends")
public class FriendsController {
    @Autowired
    public FriendsService friendsService;

    /**
     * 同意好友申请
     * @return
     */
    @PostMapping("/accept")
    public R<String> accept(@RequestParam("id") String id){
        Long userId = Long.valueOf(id);
        Long friendId = BaseContext.getCurrentId();

        Friends friend = new Friends();
        friend.setUserId(userId);
        friend.setFriendId(friendId);
        friend.setStatus("ACCEPTED");

        Friends friend2 = new Friends();
        friend2.setUserId(friendId);
        friend2.setFriendId(userId);

        friendsService.updateById(friend);
        friendsService.save(friend2);

        return R.success("添加成功");
    }

    /**
     * 向指定用户发送好友申请
     * @param id
     * @return
     */
    @PostMapping("/apply")
    public R<String> apply(@RequestParam("id") String id){
        //当前用户id
        Long userId = BaseContext.getCurrentId();
        //申请好友id
        Long friendId = Long.valueOf(id);
        Friends apply = new Friends();
        apply.setUserId(userId);
        apply.setFriendId(friendId);
        apply.setStatus("PENDING");
        friendsService.save(apply);
        return R.success("发送成功");
    }

    /**
     * 删除好友关系
     * @param id
     * @return
     */
    @DeleteMapping("/remove")
    public R<String> delete(@RequestParam("id") String id){
        Long id1 = BaseContext.getCurrentId();
        Long id2 = Long.valueOf(id);
        LambdaQueryWrapper<Friends> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Friends::getUserId, id1).eq(Friends::getFriendId, id2);
        friendsService.remove(queryWrapper1);

        LambdaQueryWrapper<Friends> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(Friends::getUserId, id2).eq(Friends::getFriendId, id1);
        friendsService.remove(queryWrapper2);

        return R.success("删除成功");
    }

    /**
     * 查询当前用户的所有好友
     * @return
     */
    @GetMapping
    public R<List<Users>> friends(){
        Long userId = BaseContext.getCurrentId();
        LambdaQueryWrapper<Friends> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Friends::getUserId, userId);

        return R.success(null);
    }
}
