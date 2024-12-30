package bibliotecaApp.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spaceInvaders/api/v1/users")
public final class UserController {

    @PostMapping
    public String Create(){
        return "Llamaron a crear";
    }

    @PutMapping
    public String Update(){
        return "Llamaron a actualizar";
    }

    @DeleteMapping
    public String Delete(){
        return "Llamaron a eliminar";
    }

    @GetMapping
    public String Retrieve(){
        return "Llamaron a consultar";
    }


}
