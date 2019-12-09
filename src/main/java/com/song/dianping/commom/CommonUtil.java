package com.song.dianping.commom;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class CommonUtil {

    public static String processErrorString(BindingResult bindingResult){

        if (!bindingResult.hasErrors()){
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (FieldError fieldError:bindingResult.getFieldErrors()){
            stringBuilder.append(fieldError.getDefaultMessage()).append(",");
        }

        String result = stringBuilder.substring(0, stringBuilder.length() - 1);

        return result;
    }
}
