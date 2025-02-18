package agus.ramdan.cdt.event.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "event_logs")
@Data
public class EventLog {
    @Id
    private String id;

    @Field("request_id")
    private String requestId;

    @Field("terminal_id")
    private String terminalId;

    @Field("event_type")
    private String eventType;

    @Field("message")
    private String message;

    @Field("timestamp")
    private long timestamp;
}


