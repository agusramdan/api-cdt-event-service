package agus.ramdan.cdt.event.repository;

import agus.ramdan.cdt.event.domain.EventLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventLogRepository extends MongoRepository<EventLog, String> {
}

