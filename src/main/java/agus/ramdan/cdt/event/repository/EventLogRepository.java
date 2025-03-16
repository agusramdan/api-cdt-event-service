package agus.ramdan.cdt.event.repository;

import agus.ramdan.cdt.event.domain.EventLogData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventLogRepository extends MongoRepository<EventLogData, String> {
}

