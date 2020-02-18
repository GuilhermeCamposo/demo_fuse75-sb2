package demo.fuse.message.converter;

import demo.fuse.message.entity.Message;
import org.apache.camel.Converter;
import org.apache.camel.TypeConverters;

public class MessageConverter implements TypeConverters {

    @Converter
    public Message toMessage(String message){
        Message msg =  new Message();
        msg.setMessage(message);
        return msg;
    }
}
