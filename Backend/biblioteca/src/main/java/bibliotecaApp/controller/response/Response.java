package bibliotecaApp.controller.response;

import crosscutting.helpers.ObjectHelper;

import java.util.ArrayList;
import java.util.List;

public abstract class Response<T> {

    private List<String> messages= new ArrayList<>();

    public final void setMessages(List<String> messages){
        this.messages=ObjectHelper.getDefault(messages,this.messages);

    }

    public List<String> getMessages() {
        return messages;
    }
}
