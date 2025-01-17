package bibliotecaApp.BusinessLogic.usecase.user.impl.rules.impl;

import bibliotecaApp.BusinessLogic.usecase.user.impl.rules.ValidateIfUserNameIsValid;
import bibliotecaApp.Exceptions.BusinessLogicException;
import crosscutting.helpers.TextHelper;
import crosscutting.messages.ErrorMessage;

public class ValidateIfPasswordIsValidImpl implements ValidateIfUserNameIsValid {
    @Override
    public void execute(final String data) {
        validateLen(data);
        validateFormat(data);
        validateNotNull(data);

        System.out.println("password Valido");
    }

    private void validateLen(final String data){
        if (!(7<=data.length()&&data.length()<=20)){
            System.out.println("Error longitud passw");
            throw BusinessLogicException.create(ErrorMessage.VALIDATE_IF_USERNAME_IS_VALID.getUserMessage(),
                    ErrorMessage.VALIDATE_IF_USERNAME_IS_VALID.getTechnicalMessage());
        }
    }

    private void validateFormat(final String data){
        if(!TextHelper.containsLettersAndNumbers(data)){
            System.out.println("Error format nombre");
            throw BusinessLogicException.create(ErrorMessage.VALIDATE_IF_USERNAME_IS_VALID.getUserMessage(),
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
