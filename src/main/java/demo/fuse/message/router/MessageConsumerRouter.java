package demo.fuse.message.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumerRouter extends RouteBuilder
{

    @Value("${sleep.time}")
    private long sleepTime;

    @Override
    public void configure() throws Exception {

        from("{{queue.save.msg}}")
            .routeId("jms-route")
        .transacted()
        .log("Got Message: ${body}")
        .process(exchange -> Thread.sleep(sleepTime))
        .to("{{queue.move.msg}}")
        .log("message moved");

    }
}
