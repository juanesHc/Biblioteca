package bibliotecaApp.controller.response;

import bibliotecaApp.controller.response.concrete.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public final class GenerateResponse<T>{

    public static ResponseEntity<GenericResponse> generateSuccesResponse(final List<String> messages){
       var genericResponse=new GenericResponse();
       genericResponse.setMessages(messages);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    public static ResponseEntity<GenericResponse> generateFailedResponse(final List<String> messages){
        var genericResponse=new GenericResponse();
        genericResponse.setMessages(messages);
        return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<T> generateSuccesResponseWithData(final T responseWithData){
        return new ResponseEntity<>(responseWithData,HttpStatus.OK);
    }

    public ResponseEntity<T> generateFailedResponseWithData(final T responseWithData){
        return new ResponseEntity<>(responseWithData,HttpStatus.BAD_REQUEST);
    }

}
