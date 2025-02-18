package agus.ramdan.cdt.event.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Builder
@Getter
public class EventResponse {

    @JsonProperty("request_id")
    private String requestId;
    private long timestamp;
    private String message;
}
