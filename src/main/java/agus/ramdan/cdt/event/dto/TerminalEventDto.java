package agus.ramdan.cdt.event.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Data
@Builder
public class TerminalEventDto {

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("timestamp")
    private long timestamp;

    private int level;

    @Field("terminal_id")
    private String terminalId;

    private Map<String,Object> data;

}


