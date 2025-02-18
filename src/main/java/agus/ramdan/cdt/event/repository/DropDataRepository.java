package agus.ramdan.cdt.event.repository;

import agus.ramdan.cdt.event.domain.DropData;
import agus.ramdan.cdt.event.domain.RawData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DropDataRepository extends MongoRepository<DropData, String> {
}

