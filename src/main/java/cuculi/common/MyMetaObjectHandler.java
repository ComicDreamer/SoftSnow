package cuculi.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 继承Mybatis-Plus提供的接口，可以实现对数据插入时进行自动值设置
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("插入数据");
        metaObject.setValue("createdAt", LocalDateTime.now());
//        metaObject.setValue("updateTime", LocalDateTime.now());
//        metaObject.setValue("createUser", BaseContext.getCurrentId());
//        metaObject.setValue("updateUser", BaseContext.getCurrentId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("更新数据");
        metaObject.setValue("updateTime", LocalDateTime.now());
//        metaObject.setValue("updateUser", BaseContext.getCurrentId());
    }
}
