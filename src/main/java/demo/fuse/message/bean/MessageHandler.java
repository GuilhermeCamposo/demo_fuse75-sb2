package demo.fuse.message.bean;

import demo.fuse.message.entity.Message;
import org.apache.camel.Exchange;
import java.util.Date;

public class MessageHandler {

    public void addData(Exchange exchange){
        Message  msg =  exchange.getIn().getBody(Message.class);

        if(msg != null){
            msg.setTime(new Date());
        }

        exchange.getOut().setBody(msg);
    }
}
