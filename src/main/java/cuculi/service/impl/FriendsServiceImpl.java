package cuculi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cuculi.mapper.FriendsMapper;
import cuculi.pojo.Friends;
import cuculi.service.FriendsService;
import org.springframework.stereotype.Service;

@Service
public class FriendsServiceImpl extends ServiceImpl<FriendsMapper, Friends> implements FriendsService {
}
