package agus.ramdan.cdt.event.service;

import agus.ramdan.cdt.event.dto.GatewayCallbackDTO;
import agus.ramdan.cdt.event.dto.RawDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    public void sendRawDTO(RawDTO event) {
        kafkaTemplate.send("raw-dto-topic", event);
    }
//    public void send(TerminalStatusDTO event) {
//        kafkaTemplate.send("terminal-status-dto-topic", event);
//    }
    public void sendGatewayCallbackDTO(GatewayCallbackDTO event) {
        kafkaTemplate.send("gateway-callback-topic", event);
    }

}
