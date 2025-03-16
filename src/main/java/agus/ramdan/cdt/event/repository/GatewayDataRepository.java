package agus.ramdan.cdt.event.repository;

import agus.ramdan.cdt.event.domain.DropData;
import agus.ramdan.cdt.event.domain.GatewayCallbackData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GatewayDataRepository extends MongoRepository<GatewayCallbackData, String> {
}

