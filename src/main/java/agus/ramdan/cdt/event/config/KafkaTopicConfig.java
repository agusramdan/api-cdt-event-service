package agus.ramdan.cdt.event.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.beans.factory.annotation.Value;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

//    @Bean
//    public NewTopic eventLogTopic() {
//        return new NewTopic("event-log-topic", 3, (short) 1);
//    }
//
//    @Bean
//    public NewTopic terminalStatusTopic() {
//        return new NewTopic("terminal-status-topic", 3, (short) 1);
//    }
//
//    @Bean
//    public NewTopic rawDataTopic() {
//        return new NewTopic("raw-data-topic", 3, (short) 1);
//    }
//
//    @Bean
//    public NewTopic dropDataTopic() {
//        return new NewTopic("drop-data-topic", 3, (short) 1);
//    }
//
//    @Bean
//    public NewTopic rawListTopic() {
//        return new NewTopic("raw-list-topic", 3, (short) 1);
//    }
//
//    @Bean
//    public NewTopic rawMapTopic() {
//        return new NewTopic("raw-map-topic", 3, (short) 1);
//    }
}

