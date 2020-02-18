package demo.fuse.message.configuration;

import demo.fuse.message.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConvertersConfig {

        @Bean(value ="myMessageConverter")
    public MessageConverter messageConverter(){
        return new MessageConverter();
    }
}
