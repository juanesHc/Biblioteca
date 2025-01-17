package bibliotecaApp.controller.response.concrete;

import bibliotecaApp.controller.response.Response;

import java.util.List;

public final class GenericResponse extends Response {

public static final Response build(final List<String> messages){
    var response=new GenericResponse();
    response.setMessages(messages);
    return response;

}


}
