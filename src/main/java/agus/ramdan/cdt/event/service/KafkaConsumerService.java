package agus.ramdan.cdt.event.service;

import agus.ramdan.cdt.event.domain.TerminalStatus;
import agus.ramdan.cdt.event.domain.DropData;
import agus.ramdan.cdt.event.domain.EventLog;
import agus.ramdan.cdt.event.domain.RawData;
import agus.ramdan.cdt.event.dto.RawListDto;
import agus.ramdan.cdt.event.dto.RawMapDto;
import agus.ramdan.cdt.event.dto.RawProcessDto;
import agus.ramdan.cdt.event.dto.TerminalEventDto;
import agus.ramdan.cdt.event.repository.TerminalStatusRepository;
import agus.ramdan.cdt.event.repository.DropDataRepository;
import agus.ramdan.cdt.event.repository.EventLogRepository;
import agus.ramdan.cdt.event.repository.RawDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final EventLogRepository eventLogRepository;
    private final TerminalStatusRepository terminalStatusRepository;
    private final RawDataRepository rawDataRepository;
    private final DropDataRepository dropDataRepository;
    private final KafkaProducerService kafkaProducerService;

    @KafkaListener(topics = "event-log-topic", groupId = "event-group")
    public void consumeEventLog(EventLog event) {
        log.info("Consumed EventLog: {}", event.getRequestId());
        eventLogRepository.save(event);
    }
    @KafkaListener(topics = "terminal-status-topic", groupId = "event-group")
    public void consumeTerminalStatus(TerminalStatus event) {
        log.info("Consumed TerminalStatus: {}", event.getTerminalId());
        terminalStatusRepository.save(event);
    }

    @KafkaListener(topics = "raw-data-topic", groupId = "event-group")
    public void consumeRawData(RawData event) {
        log.info("Consumed RawData: {}", event.getRequestId());
        rawDataRepository.save(event);
        val prosess = RawProcessDto.builder().level(1)
                .requestId(event.getRequestId())
                .timestamp(event.getTimestamp())
                .data(event.getData())
                .build();
        kafkaProducerService.send(prosess);

    }

    @KafkaListener(topics = "raw-process-topic", groupId = "event-group")
    public void consumeRawProcessDto(RawProcessDto event) {
        log.info("Consumed RawProcessDto: {}", event.getRequestId());
        val data = event.getData();
        if (data instanceof Map){
            kafkaProducerService.send(
                    RawMapDto.builder()
                            .requestId(event.getRequestId())
                            .timestamp(event.getTimestamp())
                            .level(event.getLevel())
                            .data((Map<String, Object>) data)
                            .build()
            );
        }else if (data instanceof Iterable){
            kafkaProducerService.send(
                    RawListDto.builder()
                            .requestId(event.getRequestId())
                            .timestamp(event.getTimestamp())
                            .level(event.getLevel()+1)
                            .data((List<Object>)data)
                            .build()
            );
        } else {
            log.error("Data not valid");
            kafkaProducerService.send(
                    DropData.builder()
                            .requestId(event.getRequestId())
                            .timestamp(event.getTimestamp())
                            .level(event.getLevel())
                            .data(data)
                            .build()
            );
        }

    }

    @KafkaListener(topics = "raw-map-topic", groupId = "event-group")
    public void consumeRawMapDto(RawMapDto event) {
        log.info("Consumed RawMapDto: {}", event.getRequestId());
        if(event.getData().containsKey("terminal_id") && event.getData().containsKey("timestamp")) {
            kafkaProducerService.send(
                    event.getData().get("timestamp") instanceof Long ?
                            TerminalEventDto.builder()
                                    .terminalId((String) event.getData().get("terminal_id"))
                                    .timestamp((Long) event.getData().get("timestamp"))
                                    .data(event.getData())
                                    .build() :
                            TerminalEventDto.builder()
                                    .terminalId((String) event.getData().get("terminal_id"))
                                    .timestamp(Long.parseLong(event.getData().get("timestamp").toString()))
                                    .data(event.getData())
                                    .build()
            );
        } else {
            for (Map.Entry<String, Object> entry : event.getData().entrySet()) {
                kafkaProducerService.send(
                        RawProcessDto.builder()
                                .requestId(event.getRequestId())
                                .timestamp(event.getTimestamp())
                                .level(event.getLevel() + 1)
                                .key(entry.getKey())
                                .data(entry.getValue())
                                .build()
                );
            }
        }
    }
    @KafkaListener(topics = "raw-list-topic", groupId = "event-group")
    public void consumeRawListDto(RawListDto event) {
        log.info("Consumed RawListDto: {}", event.getRequestId());
        for (Object data : event.getData()) {
            kafkaProducerService.send(
                    RawProcessDto.builder()
                            .requestId(event.getRequestId())
                            .timestamp(event.getTimestamp())
                            .level(event.getLevel() + 1)
                            .data(data)
                            .build()
            );
        }
    }

    @KafkaListener(topics = "drop-data-topic", groupId = "event-group")
    public void consumeDropData(DropData event) {
        log.info("Consumed DropData: {}", event.getRequestId());
        dropDataRepository.save(event);
    }
}
