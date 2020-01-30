package com.soliduslabs.service;

import com.soliduslabs.domain.Message;
import com.soliduslabs.dao.jpa.MessageRepository;
import com.soliduslabs.utils.Config;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Sample service to demonstrate what the API would use to get things done
 */
@Service
public class MessageService {

    private static final Logger log = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private MessageRepository messageRepository;


    public MessageService() {
    }

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }


    public List<Message> getAll() {
        return Config.getCollectionFromIteralbe(messageRepository.findAll());
    }

}
