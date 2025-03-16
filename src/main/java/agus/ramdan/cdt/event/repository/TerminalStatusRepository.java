package agus.ramdan.cdt.event.repository;

import agus.ramdan.cdt.event.domain.TerminalStatusData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TerminalStatusRepository extends MongoRepository<TerminalStatusData, String> {
}

