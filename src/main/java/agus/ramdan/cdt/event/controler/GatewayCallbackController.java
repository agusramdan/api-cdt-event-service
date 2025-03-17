package agus.ramdan.cdt.event.controler;

import agus.ramdan.cdt.event.domain.GatewayCallbackData;
import agus.ramdan.cdt.event.dto.EventResponse;
import agus.ramdan.cdt.event.mapping.GatewayCallbackMapper;
import agus.ramdan.cdt.event.repository.GatewayDataRepository;
import agus.ramdan.cdt.event.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/cdt/gateway/callback")
@RequiredArgsConstructor
public class GatewayCallbackController {

    private final KafkaProducerService kafkaProducerService;
    private final GatewayDataRepository gatewayDataRepository;
    private final GatewayCallbackMapper gatewayCallbackMapper;
    @PostMapping("/{gateway_code}")
    public ResponseEntity<EventResponse> publishEvent(@PathVariable("gateway_code") String gatewayCode,@RequestBody Object event) {
        val rawRequest = GatewayCallbackData.builder()
                .gatewayCode(gatewayCode)
                .timestamp(Instant.now().getEpochSecond())
                .data(event)
                .build();
        gatewayDataRepository.save(rawRequest);
        val dto = gatewayCallbackMapper.mapToGatewayCallbackDTO(rawRequest);
        kafkaProducerService.send(dto);
        return ResponseEntity.accepted().body(
                EventResponse.builder()
                        .timestamp(rawRequest.getTimestamp())
                        .message("Accept Callback Data").build()
        );
    }
}
