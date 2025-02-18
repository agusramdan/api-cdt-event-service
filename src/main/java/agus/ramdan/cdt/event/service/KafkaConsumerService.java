package agus.ramdan.cdt.event.service;

import agus.ramdan.cdt.event.domain.TerminalStatus;
import agus.ramdan.cdt.event.domain.DropData;
import agus.ramdan.cdt.event.domain.EventLog;
import agus.ramdan.cdt.event.domain.RawData;
import agus.ramdan.cdt.event.repository.TerminalStatusRepository;
import agus.ramdan.cdt.event.repository.DropDataRepository;
import agus.ramdan.cdt.event.repository.EventLogRepository;
import agus.ramdan.cdt.event.repository.RawDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final EventLogRepository eventLogRepository;
    private final TerminalStatusRepository terminalStatusRepository;
    private final RawDataRepository rawDataRepository;
    private final DropDataRepository dropDataRepository;
//
//    @KafkaListener(topics = "event-log-topic", groupId = "event-group")
//    public void consume(EventLog event) {
//        eventLogRepository.save(event);
//    }
//
//    @KafkaListener(topics = "terminal-status-topic", groupId = "event-group")
//    public void consume(TerminalStatus event) {
//        terminalStatusRepository.save(event);
//    }
//
//    @KafkaListener(topics = "raw-data-topic", groupId = "event-group")
//    public void consume(RawData event) {
//        rawDataRepository.save(event);
//    }
//    @KafkaListener(topics = "drop-data-topic", groupId = "event-group")
//    public void consume(DropData event) {
//        dropDataRepository.save(event);
//    }
}
