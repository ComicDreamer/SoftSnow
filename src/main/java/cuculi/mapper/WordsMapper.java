package cuculi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cuculi.pojo.Words;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WordsMapper extends BaseMapper<Words> {
}
