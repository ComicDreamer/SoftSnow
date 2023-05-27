package cuculi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cuculi.mapper.UserWordsMapper;
import cuculi.pojo.UserWords;
import cuculi.service.UserWordsService;
import org.springframework.stereotype.Service;

@Service
public class UserWordsServiceImpl extends ServiceImpl<UserWordsMapper, UserWords> implements UserWordsService {
}
