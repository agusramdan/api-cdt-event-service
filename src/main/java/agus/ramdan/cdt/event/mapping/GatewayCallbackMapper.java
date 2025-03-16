package agus.ramdan.cdt.event.mapping;

import agus.ramdan.cdt.event.domain.GatewayCallbackData;
import agus.ramdan.cdt.event.domain.RawData;
import agus.ramdan.cdt.event.dto.GatewayCallbackDTO;
import agus.ramdan.cdt.event.dto.RawDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GatewayCallbackMapper {

    GatewayCallbackDTO mapToGatewayCallbackDTO(GatewayCallbackData source);

//    @Mapping(source = "id", target = "id", qualifiedByName = "stringToUUID")
//    ServiceProduct map(ServiceProductDTO source);
//
}
