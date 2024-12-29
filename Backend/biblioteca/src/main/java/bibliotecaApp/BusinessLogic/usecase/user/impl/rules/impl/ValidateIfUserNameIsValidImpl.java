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
        if (!TextHelper.lenIsValid(data,8,15)){
            throw BusinessLogicException.create(ErrorMessage.VALIDATE_IF_USERNAME_IS_VALID.getUserMessage(),
                    ErrorMessage.VALIDATE_IF_USERNAME_IS_VALID.getTechnicalMessage());
        }
    }

    private void validateFormat(final String data){
        if(!TextHelper.containsLettersAndNumbers(data)){
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
