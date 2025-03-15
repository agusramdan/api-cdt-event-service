package agus.ramdan.cdt.event.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class DropData {

    @Id
    private String id;

    @Field("request_id")
    private String requestId;

    @Field("timestamp")
    private long timestamp;

    private int level;

    private Object data;

}


