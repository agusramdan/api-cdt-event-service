package agus.ramdan.cdt.event.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EventResponse {

    @JsonProperty("request_id")
    private String requestId;
    private long timestamp;
    private String message;
}
