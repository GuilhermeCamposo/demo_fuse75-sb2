package demo.fuse.message.router;

import demo.fuse.message.entity.Message;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import javax.ws.rs.core.MediaType;


@Component
public class RestServiceRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        rest("/internal-api")
        .post("/completeMessage")
        .consumes(MediaType.APPLICATION_JSON)
        .produces(MediaType.APPLICATION_JSON)
        .type(Message.class)
        .outType(Message.class)
        .route()
        .routeId("complete-message")
        .log("${body}")
        .bean("messageHandler");

    }
}
