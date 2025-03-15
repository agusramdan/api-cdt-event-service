package agus.ramdan.cdt.event.service;

import agus.ramdan.cdt.event.domain.*;
import agus.ramdan.cdt.event.dto.RawListDto;
import agus.ramdan.cdt.event.dto.RawMapDto;
import agus.ramdan.cdt.event.dto.RawProcessDto;
import agus.ramdan.cdt.event.dto.TerminalEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(EventLog event) {
        kafkaTemplate.send("event-log-topic", event);
    }
    public void send(RawData event) {
        kafkaTemplate.send("raw-data-topic", event);
    }
    public void send(TerminalStatus event) {
        kafkaTemplate.send("terminal-status-topic", event);
    }
    public void send(RawListDto event) {
        kafkaTemplate.send("raw-list-topic", event);
    }
    public void send(RawMapDto event) {
        kafkaTemplate.send("raw-map-topic", event);
    }
    public void send(RawProcessDto process) {
        kafkaTemplate.send("raw-process-topic", process);
    }

    public void send(DropData build) {
        kafkaTemplate.send("drop-data-topic", build);
    }

    public void send(TerminalEventDto terminalEventDto) {
        kafkaTemplate.send("terminal-event-topic", terminalEventDto);
    }
    public void send(GatewayCallbackData event) {
        kafkaTemplate.send("gateway-callback-topic", event);
    }
}
