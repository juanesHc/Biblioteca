package bibliotecaApp.controller;

import bibliotecaApp.BusinessLogic.facade.user.RegisterNewUserFacade;
import bibliotecaApp.BusinessLogic.facade.user.impl.RegisterNewUserFacadeImpl;
import bibliotecaApp.DTO.UserDTO;
import bibliotecaApp.controller.response.GenerateResponse;
import bibliotecaApp.controller.response.concrete.GenericResponse;
import bibliotecaApp.controller.response.concrete.UserResponse;
import crosscutting.exceptions.BibliotecaApplicationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/SpaceInvaders/api/v1/users")
public final class UserController {

    @GetMapping("/dummy")
    public UserDTO getDummy(){
        return new UserDTO();
    }
    @PostMapping
    public ResponseEntity<GenericResponse> Create(@RequestBody UserDTO user){
        var messages=new ArrayList<String>();
        try{

            RegisterNewUserFacade registerNewUser=new RegisterNewUserFacadeImpl();
            registerNewUser.execute(user);

            messages.add("El usuario se registró de forma Satisfactoria");
            return GenerateResponse.generateSuccesResponse(messages);

        }catch (final BibliotecaApplicationException exception){

            messages.add(exception.getUserMessage());
            System.out.println(exception);
            return GenerateResponse.generateFailedResponse(messages);

        }catch (final Exception exception){
            messages.add("Se ha presentado un problema innesperado");
            System.out.println(exception);
            return GenerateResponse.generateFailedResponse(messages);
        }



    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse> Update(@PathVariable String id,@RequestBody UserDTO user){
        var messages=new ArrayList<String>();

        messages.add("El usuario se actualizó de forma s}Satisfactoria");
        return GenerateResponse.generateSuccesResponse(messages);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse> Delete(@PathVariable String id){
        var messages=new ArrayList<String>();

        messages.add("El usuario se eliminó de forma s}Satisfactoria");
        return GenerateResponse.generateSuccesResponse(messages);
    }

    @GetMapping
    public ResponseEntity<UserResponse> RetrieveAll(){
        var responseWithData=new UserResponse();

        var messages=new ArrayList<String>();

        messages.add("Los usuarios se consultarón de forma s}Satisfactoria");

        var  List=new ArrayList<UserDTO>();
        List.add(getDummy());
        List.add(getDummy());
        List.add(getDummy());

        responseWithData.setData(List);
        responseWithData.setMessages(messages);
        return ((new GenerateResponse<UserResponse>()).generateSuccesResponseWithData(responseWithData));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> RetrieveById(@PathVariable String id){
        var responseWithData=new UserResponse();

        var messages=new ArrayList<String>();

        messages.add("El usuario se consultó de forma s}Satisfactoria");

        var  List=new ArrayList<UserDTO>();
        List.add(getDummy());

        responseWithData.setData(List);
        responseWithData.setMessages(messages);
        return ((new GenerateResponse<UserResponse>()).generateSuccesResponseWithData(responseWithData));
    }

}
