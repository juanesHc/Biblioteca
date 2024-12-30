package bibliotecaApp.controller.response.concrete;

import bibliotecaApp.DTO.UserDTO;
import bibliotecaApp.controller.response.Response;
import bibliotecaApp.controller.response.ResponseWithData;

import java.util.List;

public final class UserResponse extends ResponseWithData<UserDTO> {

    public static final Response build(final List<String> messages,final List<UserDTO> data){
        var response=new UserResponse();
        response.setMessages(messages);
        response.setData(data);
        return response;

    }



}
