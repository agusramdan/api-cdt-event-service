package agus.ramdan.cdt.event.mapping;

import agus.ramdan.cdt.event.domain.RawData;
import agus.ramdan.cdt.event.dto.RawDTO;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface RawDataMapper {
    RawDTO mapToRawDTO(RawData source);

//    @Mapping(source = "id", target = "id", qualifiedByName = "stringToUUID")
//    ServiceProduct map(ServiceProductDTO source);
//
//    @Mapping(source = "id", target = "id", qualifiedByName = "uuidToString")
//    ServiceProductDTO map(ServiceProduct source);
//
//    @Mapping(source = "id", target = "id", qualifiedByName = "stringToUUID")
//    BeneficiaryAccount mapBeneficiaryAccount(BeneficiaryAccountDTO source);
//
//    @Mapping(source = "id", target = "id", qualifiedByName = "uuidToString")
//    BeneficiaryAccountDTO map(BeneficiaryAccount source);
//    @AfterMapping
//    default void handleException(@MappingTarget BeneficiaryAccountDTO dto, BeneficiaryAccount entity) {
//        try {
//            dto.setId(entity.getId().toString());
//        } catch (Exception e) {
//            dto.setAccount_number("Unknown");
//        }
//    }
//    @Mapping(source = "id", target = "id", qualifiedByName = "stringToUUID")
//    ServiceTransaction map(ServiceTransactionDTO source);
//
//    @Mapping(source = "id", target = "id", qualifiedByName = "uuidToString")
//    ServiceTransactionDTO map(ServiceTransaction source);
//
//    @Mapping(source = "customer_crew_id", target = "id", qualifiedByName = "stringToUUID")
//    CustomerCrew map(TrxUserDTO source);
//
//    @Mapping(source = "id", target = "customer_crew_id", qualifiedByName = "uuidToString")
//    TrxUserDTO map(CustomerCrew source);
//
//    String map(QRCodeType source);
//
//    default QRCodeType mapQRCodeType(String source) {
//        if (source == null) return null;
//        return QRCodeType.valueOf(source);
//    }
//

}
