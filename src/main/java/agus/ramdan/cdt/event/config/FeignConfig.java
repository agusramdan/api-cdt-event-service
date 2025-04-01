package agus.ramdan.cdt.event.config;


import feign.Capability;
import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {
        "agus.ramdan.cdt.core.trx.controller.client"}
)
public class FeignConfig {

//    @Bean
//    public RequestInterceptor requestInterceptor() {
//        return new AuthorizationRequestInterceptor();
//    }
    @Bean
    public Capability capability(final MeterRegistry registry) {
        return new MicrometerCapability(registry);
    }
//    @Bean
//  /  public ErrorDecoder errorDecoder() {
//        return new CustomFeignErrorDecoder();
//    }
}
