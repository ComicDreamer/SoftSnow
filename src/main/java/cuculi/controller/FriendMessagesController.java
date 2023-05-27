package cuculi.controller;

import cuculi.service.FriendMessagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/friendmessages")
public class FriendMessagesController {
    @Autowired
    public FriendMessagesService friendMessagesService;


}
