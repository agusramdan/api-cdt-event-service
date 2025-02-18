package agus.ramdan.cdt.event.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "raw_data")
@Data
@Builder
public class RawData {

    @Id
    private String id;

    @Field("request_id")
    private String requestId;

    @Field("timestamp")
    private long timestamp;

    private Object data;



}


