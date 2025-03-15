package agus.ramdan.cdt.event.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "gateway_callback_data")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayCallbackData {
    @Id
    private String id;

    @Field("gateway_code")
    private String gatewayCode;

    @Field("timestamp")
    private long timestamp;

    private Object data;

}


