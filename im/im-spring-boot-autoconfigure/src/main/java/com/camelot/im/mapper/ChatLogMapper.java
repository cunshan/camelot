package com.camelot.im.mapper;

import com.camelot.im.domain.ChatLog;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ChatLogMapper {

  List<ChatLog> queryAllChatLogs(@Param("param") ChatLog param);


}
