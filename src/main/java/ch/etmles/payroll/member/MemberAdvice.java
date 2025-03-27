package ch.etmles.payroll.member;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MemberAdvice {

    @ResponseBody
    @ExceptionHandler(MemberAlreadyInTeam.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String memberAlreadyInATeam(MemberAlreadyInTeam ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(MemberNotInTeam.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String memberNotInATeam(MemberNotInTeam ex){
        return ex.getMessage();
    }
}
