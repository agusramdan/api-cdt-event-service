package agus.ramdan.cdt.event.repository;

import agus.ramdan.cdt.event.domain.EventLog;
import agus.ramdan.cdt.event.domain.RawData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RawDataRepository extends MongoRepository<RawData, String> {
}

