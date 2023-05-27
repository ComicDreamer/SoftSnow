package cuculi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cuculi.mapper.FriendMessagesMapper;
import cuculi.pojo.FriendMessages;
import cuculi.service.FriendMessagesService;
import org.springframework.stereotype.Service;

@Service
public class FriendMessagesServiceImpl extends ServiceImpl<FriendMessagesMapper, FriendMessages> implements FriendMessagesService {
}
