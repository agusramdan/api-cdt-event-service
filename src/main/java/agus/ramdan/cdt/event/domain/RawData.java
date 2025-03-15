package agus.ramdan.cdt.event.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "raw_data")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RawData {

    @Id
    private String id;

    @Field("request_id")
    private String requestId;

    @Field("timestamp")
    private long timestamp;

    private Object data;



}


