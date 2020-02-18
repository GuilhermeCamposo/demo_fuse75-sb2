package demo.fuse.message.router;

import demo.fuse.message.entity.Message;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;
import javax.ws.rs.core.MediaType;

/**
 * A simple Camel REST DSL route that implements the greetings service.
 * 
 */
@Component
public class MessageSaverRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // @formatter:off
        restConfiguration()
            .apiContextPath("/api-doc")
            .apiProperty("api.title", "Message Sender REST API")
            .apiProperty("api.version", "1.0")
            .apiProperty("cors", "true")
            .apiProperty("base.path", "camel/")
            .apiProperty("api.path", "/")
            .apiProperty("host", "")
            .apiContextRouteId("doc-api")
        .component("servlet")
        .bindingMode(RestBindingMode.json);

        rest("/message")
        .description("Interact with your messages")
            .post("/")
                .description("sends your message to the broker")
                .consumes(MediaType.TEXT_PLAIN)
                .produces(MediaType.TEXT_PLAIN)
                .type(String.class)
            .route()
             .routeId("message-save-api-post")
            .log("Received Message: ${body}")
            .setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON))
            .convertBodyTo(Message.class)
            .marshal().json(JsonLibrary.Gson)
            .to("{{to.rest.endpoint}}")
            .inOnly("{{queue.save.message}}")
            .setBody(simple("message was: ${body}"));

        // @formatter:on
    }

}