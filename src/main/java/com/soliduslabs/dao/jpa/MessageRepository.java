package com.soliduslabs.dao.jpa;

import com.soliduslabs.domain.Message;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Repository can be used to delegate CRUD operations against the data source
 */
public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {
    List<Message> findByMessage(String message);
}
