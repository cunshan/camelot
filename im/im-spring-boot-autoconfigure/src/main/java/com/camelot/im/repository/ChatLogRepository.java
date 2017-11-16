package com.camelot.im.repository;

import com.camelot.im.domain.ChatLog;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ChatLogRepository extends CrudRepository<ChatLog,Long> {

  List<ChatLog> findAllByFromUserIdAndToUserId(Long fromUserId, Long toUserId);

}
