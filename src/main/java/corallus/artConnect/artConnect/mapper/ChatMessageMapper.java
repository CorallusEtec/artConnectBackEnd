package corallus.artConnect.artConnect.mapper;

import corallus.artConnect.artConnect.dto.response.ChatMessageResponse;
import corallus.artConnect.artConnect.entity.ChatMessage;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatMessageMapper {
    ChatMessageResponse toDTO(ChatMessage entity);

    List<ChatMessageResponse> toDTOList(List<ChatMessage> entityList);
}
