package org.teomant.controller.validator;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.teomant.controller.form.AttemptForm;

@Component

public class AttemptValidator implements Validator {

    @Override
    public boolean supports( Class<?> aClass ){
        return AttemptForm.class.equals( aClass );
    }

    @Override
    public void validate( Object o , Errors errors ){

        AttemptForm form = ( AttemptForm ) o;

        try{
            Integer.parseInt(form.getAttempt());
        } catch (NumberFormatException e){
            errors.rejectValue("attempt", "", "Use only numbers!");
            return;
        }

        if (form.getAttempt().length()!=4){
            errors.rejectValue("attempt", "", "4 digits at once!");
        }
    }
}
