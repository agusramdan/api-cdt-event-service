package agus.ramdan.cdt.event.controler;

import agus.ramdan.base.exception.BadRequestException;
import agus.ramdan.base.exception.ErrorValidation;
import agus.ramdan.cdt.event.domain.RawData;
import agus.ramdan.cdt.event.dto.EventResponse;
import agus.ramdan.cdt.event.dto.RawDTO;
import agus.ramdan.cdt.event.mapping.RawDataMapper;
import agus.ramdan.cdt.event.repository.RawDataRepository;
import agus.ramdan.cdt.event.service.KafkaProducerService;
import agus.ramdan.cdt.event.utils.LongUtils;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/cdt/cdm/events")
@RequiredArgsConstructor
public class EventController {
    private final KafkaProducerService kafkaProducerService;
    private final RawDataRepository rawDataRepository;
    private final RawDataMapper rawDataMapper;
    @PostMapping
    public ResponseEntity<EventResponse> publishEvent(@RequestBody Object event) {
        if (event instanceof Map){
            Map ev = (Map) event;
            if (ev.containsKey("terminal_id")){
                if (!ev.containsKey("timestamp") && !ev.containsKey("update_on")){
                    BadRequestException.ThrowWhenError("Invalid data without update_on or timestamp ",
                            List.of(new ErrorValidation("Invalid","update_on",null)),
                            event);
                }
                if (ev.containsKey("timestamp")){
                    if (!LongUtils.canConvertToLong(ev.get("timestamp"))){
                        BadRequestException.ThrowWhenError("Invalid data timestamp",
                                List.of(new ErrorValidation("Invalid","timestamp",ev.get("timestamp"))),
                                event);
                    }
                }
                if (ev.containsKey("update_on")){
                    if (!LongUtils.canConvertToLong(ev.get("update_on"))){
                        BadRequestException.ThrowWhenError("Invalid data update_on",
                                List.of(new ErrorValidation("Invalid","update_on",ev.get("update_on"))),
                                event);
                    }
                }
            }
        }

        val requestId = UUID.randomUUID();
        val rawRequest = RawData.builder()
                .requestId(requestId.toString())
                .timestamp(Instant.now().getEpochSecond())
                .data(event)
                .build();

        rawDataRepository.save(rawRequest);
        RawDTO rawDTO = rawDataMapper.mapToRawDTO(rawRequest);
        kafkaProducerService.sendRawDTO(rawDTO);
        return ResponseEntity.accepted().body(
                EventResponse.builder()
                        .requestId(rawRequest.getRequestId())
                        .timestamp(rawRequest.getTimestamp())
                        .message("Accept Event Data").build()
        );
    }
}