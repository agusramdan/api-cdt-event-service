package agus.ramdan.cdt.event.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Data Tidak dapat di prosess dengan alasan salah satu dibawah ini.
 * 1. Map tidak mempunyai attribut terminal_id,timestamp sehingga
 */
@Document(collection = "drop_data")
@Data
@Builder
public class DropData {

    @Id
    private String id;

    @Field("request_id")
    private String requestId;

    @Field("timestamp")
    private long timestamp;

    private Object data;

}


