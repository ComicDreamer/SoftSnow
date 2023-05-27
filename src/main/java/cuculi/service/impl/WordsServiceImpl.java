package cuculi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cuculi.mapper.WordsMapper;
import cuculi.pojo.Words;
import cuculi.service.WordsService;
import org.springframework.stereotype.Service;

@Service
public class WordsServiceImpl extends ServiceImpl<WordsMapper, Words> implements WordsService {
}
