package cuculi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cuculi.mapper.UsersMapper;
import cuculi.pojo.Users;
import cuculi.service.UsersService;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements  UsersService{
}
