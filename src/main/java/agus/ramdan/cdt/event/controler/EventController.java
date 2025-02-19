package agus.ramdan.cdt.event.controler;

import agus.ramdan.cdt.event.domain.RawData;
import agus.ramdan.cdt.event.dto.EventResponse;
import agus.ramdan.cdt.event.repository.RawDataRepository;
import agus.ramdan.cdt.event.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/cdt/cdm/events")
@RequiredArgsConstructor
public class EventController {

    private final KafkaProducerService kafkaProducerService;
    private final RawDataRepository rawDataRepository;
    @PostMapping
    public ResponseEntity<EventResponse> publishEvent(@RequestBody Object event) {
        val requestId = UUID.randomUUID();
        val rawRequest = RawData.builder()
                .requestId(requestId.toString())
                .timestamp(Instant.now().getEpochSecond())
                .data(event)
                .build();
        rawDataRepository.save(rawRequest);
        //kafkaProducerService.send(rawRequest);
        return ResponseEntity.accepted().body(
                EventResponse.builder()
                        .requestId(rawRequest.getRequestId())
                        .timestamp(rawRequest.getTimestamp())
                        .message("Accept Event Data").build()
        );
    }
}
