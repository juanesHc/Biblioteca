package bibliotecaApp.BusinessLogic.usecase.user.impl.rules.impl;

import bibliotecaApp.BusinessLogic.usecase.user.impl.rules.ValidateIfUserNameIsValid;
import bibliotecaApp.Exceptions.BusinessLogicException;
import crosscutting.helpers.TextHelper;
import crosscutting.messages.ErrorMessage;

public class ValidateIfUserNameIsValidImpl implements ValidateIfUserNameIsValid {
    @Override
    public void execute(final String data) {
        validateLen(data);
        validateFormat(data);
        validateNotNull(data);
    }

    private void validateLen(final String data){
        if (!(5<=data.length()&&data.length()<=20)){
            System.out.println("Error longitud nombre");
            throw BusinessLogicException.create("El nombre de usuario debe tener entre 7 y 20 caracteres",
                    ErrorMessage.VALIDATE_IF_USERNAME_IS_VALID.getTechnicalMessage());
        }
    }

    private void validateFormat(final String data){
        if(!TextHelper.containsLettersAndNumbers(data)){
            System.out.println("Error format nombre");
            throw BusinessLogicException.create("El nombre de usuario debe tener numeros y letras",
                    ErrorMessage.VALIDATE_IF_USERNAME_IS_VALID.getTechnicalMessage());
        }
    }

    private void validateNotNull(final String data){
        if(TextHelper.isEmptyAppplyingTrim(data)){
            throw BusinessLogicException.create(ErrorMessage.VALIDATE_IF_USERNAME_IS_VALID.getUserMessage(),
                    ErrorMessage.VALIDATE_IF_USERNAME_IS_VALID.getTechnicalMessage());
        }
    }

}
