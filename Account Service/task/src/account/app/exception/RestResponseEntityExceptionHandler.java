package account.app.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value={CantLockAdminException.class})
    @ResponseBody
    protected ResponseEntity<Object> handleDateException(CantLockAdminException ex, WebRequest request) {
        ExceptionMessageResponse exceptionMessageResponse = new ExceptionMessageResponse();
        exceptionMessageResponse.setTimestamp(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.MAX)));
        exceptionMessageResponse.setStatus(BAD_REQUEST.value());
        exceptionMessageResponse.setError("Bad Request");
        exceptionMessageResponse.setMessage("Can't lock the ADMINISTRATOR!");
        exceptionMessageResponse.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        return handleExceptionInternal(ex, exceptionMessageResponse,
                new HttpHeaders(), BAD_REQUEST, request);
    }

    @ExceptionHandler(value={TryingToAddAdminRoleException.class})
    @ResponseBody
    protected ResponseEntity<Object> handleDateException(TryingToAddAdminRoleException ex, WebRequest request) {
        ExceptionMessageResponse exceptionMessageResponse = new ExceptionMessageResponse();
        exceptionMessageResponse.setTimestamp(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.MAX)));
        exceptionMessageResponse.setStatus(BAD_REQUEST.value());
        exceptionMessageResponse.setError("Bad Request");
        exceptionMessageResponse.setMessage("The user cannot combine administrative and business roles!");
        exceptionMessageResponse.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        return handleExceptionInternal(ex, exceptionMessageResponse,
                new HttpHeaders(), BAD_REQUEST, request);
    }

    @ExceptionHandler(value={EnumNotFoundException.class})
    @ResponseBody
    protected ResponseEntity<Object> handleDateException(EnumNotFoundException ex, WebRequest request) {
        ExceptionMessageResponse exceptionMessageResponse = new ExceptionMessageResponse();
        exceptionMessageResponse.setTimestamp(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.MAX)));
        exceptionMessageResponse.setStatus(NOT_FOUND.value());
        exceptionMessageResponse.setError("Not Found");
        exceptionMessageResponse.setMessage("Role not found!");
        exceptionMessageResponse.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        return handleExceptionInternal(ex, exceptionMessageResponse,
                new HttpHeaders(), NOT_FOUND, request);
    }

    @ExceptionHandler(value={AcctServiceAdminCantHaveBusinessRolesException.class})
    @ResponseBody
    protected ResponseEntity<Object> handleDateException(AcctServiceAdminCantHaveBusinessRolesException ex, WebRequest request) {
        ExceptionMessageResponse exceptionMessageResponse = new ExceptionMessageResponse();
        exceptionMessageResponse.setTimestamp(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.MAX)));
        exceptionMessageResponse.setStatus(BAD_REQUEST.value());
        exceptionMessageResponse.setError("Bad Request");
        exceptionMessageResponse.setMessage("The user cannot combine administrative and business roles!");
        exceptionMessageResponse.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        return handleExceptionInternal(ex, exceptionMessageResponse,
                new HttpHeaders(), BAD_REQUEST, request);
    }

    @ExceptionHandler(value={AcctUserShouldHaveAtLeastOneRole.class})
    @ResponseBody
    protected ResponseEntity<Object> handleDateException(AcctUserShouldHaveAtLeastOneRole ex, WebRequest request) {
        ExceptionMessageResponse exceptionMessageResponse = new ExceptionMessageResponse();
        exceptionMessageResponse.setTimestamp(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.MAX)));
        exceptionMessageResponse.setStatus(BAD_REQUEST.value());
        exceptionMessageResponse.setError("Bad Request");
        exceptionMessageResponse.setMessage("The user must have at least one role!");
        exceptionMessageResponse.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        return handleExceptionInternal(ex, exceptionMessageResponse,
                new HttpHeaders(), BAD_REQUEST, request);
    }

    @ExceptionHandler(value={AcctUserDoesNotHaveSuchRoleException.class})
    @ResponseBody
    protected ResponseEntity<Object> handleDateException(AcctUserDoesNotHaveSuchRoleException ex, WebRequest request) {
        ExceptionMessageResponse exceptionMessageResponse = new ExceptionMessageResponse();
        exceptionMessageResponse.setTimestamp(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.MAX)));
        exceptionMessageResponse.setStatus(BAD_REQUEST.value());
        exceptionMessageResponse.setError("Bad Request");
        exceptionMessageResponse.setMessage("The user does not have a role!");
        exceptionMessageResponse.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        return handleExceptionInternal(ex, exceptionMessageResponse,
                new HttpHeaders(), BAD_REQUEST, request);
    }

    @ExceptionHandler(value={UserAdminTryToRemoveHimselfException.class})
    @ResponseBody
    protected ResponseEntity<Object> handleDateException(UserAdminTryToRemoveHimselfException ex, WebRequest request) {
        ExceptionMessageResponse exceptionMessageResponse = new ExceptionMessageResponse();
        exceptionMessageResponse.setTimestamp(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.MAX)));
        exceptionMessageResponse.setStatus(BAD_REQUEST.value());
        exceptionMessageResponse.setError("Bad Request");
        exceptionMessageResponse.setMessage("Can't remove ADMINISTRATOR role!");
        exceptionMessageResponse.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        return handleExceptionInternal(ex, exceptionMessageResponse,
                new HttpHeaders(), BAD_REQUEST, request);
    }

    @ExceptionHandler(value={AcctUserNotFoundException.class})
    @ResponseBody
    protected ResponseEntity<Object> handleDateException(AcctUserNotFoundException ex, WebRequest request) {
        ExceptionMessageResponse exceptionMessageResponse = new ExceptionMessageResponse();
        exceptionMessageResponse.setTimestamp(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.MAX)));
        exceptionMessageResponse.setStatus(NOT_FOUND.value());
        exceptionMessageResponse.setError("Not Found");
        exceptionMessageResponse.setMessage("User not found!");
        exceptionMessageResponse.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        return handleExceptionInternal(ex, exceptionMessageResponse,
                new HttpHeaders(), NOT_FOUND, request);
    }

    @ExceptionHandler(value={DataIntegrityViolationException.class})
    @ResponseBody
    protected ResponseEntity<Object> handleDateException(DataIntegrityViolationException ex, WebRequest request) {
        ExceptionMessageResponse exceptionMessageResponse = new ExceptionMessageResponse();
        exceptionMessageResponse.setTimestamp(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.MAX)));
        exceptionMessageResponse.setStatus(BAD_REQUEST.value());
        exceptionMessageResponse.setError("Bad Request");
        exceptionMessageResponse.setMessage("User with same period exception!");
        exceptionMessageResponse.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        return handleExceptionInternal(ex, exceptionMessageResponse,
                new HttpHeaders(), BAD_REQUEST, request);
    }

    @ExceptionHandler(value={DateTimeException.class})
    @ResponseBody
    protected ResponseEntity<Object> handleDateException(DateTimeException ex, WebRequest request) {
        ExceptionMessageResponse exceptionMessageResponse = new ExceptionMessageResponse();
        exceptionMessageResponse.setTimestamp(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.MAX)));
        exceptionMessageResponse.setStatus(BAD_REQUEST.value());
        exceptionMessageResponse.setError("Bad Request");
        exceptionMessageResponse.setMessage("Provide correct date!");
        exceptionMessageResponse.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        return handleExceptionInternal(ex, exceptionMessageResponse,
                new HttpHeaders(), BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {NegativeSalaryException.class})
    @ResponseBody
    protected ResponseEntity<Object> handleNegativeSalaryException(NegativeSalaryException ex, WebRequest request){
        ExceptionMessageResponse exceptionMessageResponse = new ExceptionMessageResponse();
        exceptionMessageResponse.setTimestamp(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.MAX)));
        exceptionMessageResponse.setStatus(BAD_REQUEST.value());
        exceptionMessageResponse.setError("Bad Request");
        exceptionMessageResponse.setMessage("Salary should be greater than zero!");
        exceptionMessageResponse.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        return handleExceptionInternal(ex, exceptionMessageResponse,
                new HttpHeaders(), BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    @ResponseBody
    protected ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request){
        ExceptionMessageResponse exceptionMessageResponse = new ExceptionMessageResponse();
        exceptionMessageResponse.setTimestamp(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.MAX)));
        exceptionMessageResponse.setStatus(BAD_REQUEST.value());
        exceptionMessageResponse.setError("Bad Request");
        exceptionMessageResponse.setMessage("Couldn't find user with email "+ex.getNotExistingUserEmail());
        exceptionMessageResponse.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        return handleExceptionInternal(ex, exceptionMessageResponse,
                new HttpHeaders(), BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {UserExistException.class})
    @ResponseBody
    protected ResponseEntity<Object> handleUserExistException(UserExistException ex, WebRequest request){
        ExceptionMessageResponse exceptionMessageResponse = new ExceptionMessageResponse();
        exceptionMessageResponse.setTimestamp(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.MAX)));
        exceptionMessageResponse.setStatus(BAD_REQUEST.value());
        exceptionMessageResponse.setError("Bad Request");
        exceptionMessageResponse.setMessage("User exist!");
        exceptionMessageResponse.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        return handleExceptionInternal(ex, exceptionMessageResponse,
                new HttpHeaders(), BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {PasswordWasHackedException.class})
    @ResponseBody
    protected ResponseEntity<Object> handleUserPswdWasHacked(PasswordWasHackedException ex, WebRequest request){
        ExceptionMessageResponse exceptionMessageResponse = new ExceptionMessageResponse();
        exceptionMessageResponse.setTimestamp(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.MAX)));
        exceptionMessageResponse.setStatus(BAD_REQUEST.value());
        exceptionMessageResponse.setError("Bad Request");
        exceptionMessageResponse.setMessage("The password is in the hacker's database!");
        exceptionMessageResponse.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        return handleExceptionInternal(ex, exceptionMessageResponse,
                new HttpHeaders(), BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {UserChangesPasswordSamePasswordException.class})
    @ResponseBody
    protected ResponseEntity<Object> handleUserSamePaswd(UserChangesPasswordSamePasswordException ex, WebRequest request){
        ExceptionMessageResponse exceptionMessageResponse = new ExceptionMessageResponse();
        exceptionMessageResponse.setTimestamp(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.MAX)));
        exceptionMessageResponse.setStatus(BAD_REQUEST.value());
        exceptionMessageResponse.setError("Bad Request");
        exceptionMessageResponse.setMessage("The passwords must be different!");
        exceptionMessageResponse.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        return handleExceptionInternal(ex, exceptionMessageResponse,
                new HttpHeaders(), BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        String errorMessage = fieldErrors.get(0).getDefaultMessage();
        ExceptionMessageResponse exceptionMessageResponse = new ExceptionMessageResponse();
        exceptionMessageResponse.setTimestamp(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.MAX)));
        exceptionMessageResponse.setStatus(BAD_REQUEST.value());
        exceptionMessageResponse.setError("Bad Request");
        exceptionMessageResponse.setMessage(errorMessage);
        exceptionMessageResponse.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        return ResponseEntity.badRequest().body(exceptionMessageResponse);
    }
}
