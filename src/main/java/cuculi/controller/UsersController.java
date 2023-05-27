package cuculi.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cuculi.common.BaseContext;
import cuculi.common.JwtUtils;
import cuculi.common.R;
import cuculi.pojo.Users;
import cuculi.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    public UsersService usersService;

    /**
     * 根据用户名查询用户
     * @return
     */
    @GetMapping
    public R<Users> search(){
        String username = "张三";
        LambdaQueryWrapper<Users> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Users::getUsername, username);
        Users user = usersService.getOne(queryWrapper);
        if (user != null){
            user.setPassword(null);
            return R.success(user);
        }else{
            return R.error("用户名不存在");
        }
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @PostMapping("/register")
    public R<String> register(@RequestBody Users user){
        //设置初始密码
        String initialPassword = user.getPassword();
        //进行md5加密
        user.setPassword(DigestUtils.md5DigestAsHex(initialPassword.getBytes()));

        usersService.save(user);
        return R.success("注册成功");
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public R<String> login(@RequestBody Users user, HttpSession session){
        LambdaQueryWrapper<Users> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Users::getUsername, user.getUsername());
        Users emp = usersService.getOne(queryWrapper);

        if (emp == null){
            return R.error("用户名不存在");
        }

        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        if (!emp.getPassword().equals(password)){
            return R.error("用户名和密码不匹配");
        }

        String token = JwtUtils.generateToken(emp.getId());
        log.info(token);
        return R.success("登录成功").add("token", token);
    }

    /**
     * 用户登出
     */
    @GetMapping("/logout")
    public void logout(){

    }
    /**
     * 查询用户信息
     */
    @GetMapping("/info")
    public R<Users> getUserInfo(){
        Long id = BaseContext.getCurrentId();
        Users user = usersService.getById(id);
        //将密码进行解密还原

        return R.success(user);
    }

    /**
     * 更新用户信息
     * @return
     */
    @PutMapping()
    public R<String> save(@RequestBody Users user){
        Long id = BaseContext.getCurrentId();
        //将密码进行加密
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(password);
        user.setId(id);
        usersService.updateById(user);
        return R.success("修改成功");
    }
}
