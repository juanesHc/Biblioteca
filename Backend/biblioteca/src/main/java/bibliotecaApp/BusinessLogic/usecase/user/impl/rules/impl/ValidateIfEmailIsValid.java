package bibliotecaApp.BusinessLogic.usecase.user.impl.rules.impl;

import bibliotecaApp.Exceptions.BusinessLogicException;
import crosscutting.helpers.TextHelper;
import crosscutting.messages.ErrorMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateIfEmailIsValid {

    public void execute(String email){
        isConsistent(email);
    }

    private void isConsistent(String email){
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");


        Matcher mather = pattern.matcher(email);

        if (!mather.find()) {
            throw BusinessLogicException.create(ErrorMessage.VALIDATE_IF_EMAIL_IS_VALID.getUserMessage(),
                    ErrorMessage.VALIDATE_IF_EMAIL_IS_VALID.getTechnicalMessage());

    }
}
}
