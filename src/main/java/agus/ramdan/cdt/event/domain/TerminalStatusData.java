package agus.ramdan.cdt.event.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "terminal_status")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TerminalStatusData {
    @Id
    private String id;

    @Field("terminal_id")
    private String terminalId;

    @Field("name")
    private String name;

    @Field("request_id")
    private String requestId;

    @Field("timestamp")
    private long timestamp;

    @Field("value")
    private String value;

}
