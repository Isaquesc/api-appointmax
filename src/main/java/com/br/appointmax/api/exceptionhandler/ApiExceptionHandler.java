package com.br.appointmax.api.exceptionhandler;

import com.br.appointmax.domain.constants.StringConstants;
import com.br.appointmax.domain.exception.ClientNotFoundException;
import com.br.appointmax.domain.exception.CsvUploudException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.br.appointmax.domain.constants.StringConstants.MSG_GENERICO_ERRO_SISTEMA;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<Object> handlerClientNotFoundException(ClientNotFoundException ex, WebRequest request) {

        var status = HttpStatus.BAD_GATEWAY;
        var problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        var detail = ex.getMessage();
        var problem = getProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(CsvUploudException.class)
    public ResponseEntity<Object> handlerCsvUploadException(CsvUploudException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_GATEWAY;
        var problemType = ProblemType.CSV_ERRO_UPLOAD;
        var detail = ex.getMessage();
        var problem = getProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handlerException(Exception ex, WebRequest request) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var problemType = ProblemType.ERRO_DE_SISTEMA;
        var details = MSG_GENERICO_ERRO_SISTEMA;

        ex.printStackTrace();

        var problem = getProblemBuilder(status, problemType, details)
                .userMessage(details)
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        var problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        var details = String.format("O recurso %s, que voce tentou acessar, é inexistente.", ex.getRequestURL());
        var problem = getProblemBuilder(status, problemType, details)
                .userMessage(MSG_GENERICO_ERRO_SISTEMA)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handlerMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException) ex, headers, status, request);
        }

        return super.handleTypeMismatch(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
        }

        var problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        var details = "O corpo da requisição está inválido. Verifique erro de sintaxe.";

        var problem = getProblemBuilder(status, problemType, details)
                .userMessage(MSG_GENERICO_ERRO_SISTEMA)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        var httpStatus = (HttpStatus) statusCode;
        if (body == null) {
            body = Problem.builder()
                    .title(httpStatus.getReasonPhrase())
                    .status(statusCode.value())
                    .timestamp(LocalDateTime.now())
                    .build();
        } else if (body instanceof String) {
            body = Problem.builder()
                    .title((String) body)
                    .status(statusCode.value())
                    .timestamp(LocalDateTime.now())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var path = joinPath(ex.getPath());

        var problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        var details = String.format("A propriedade '%s' recebeu o valor '%s', que é do tipo invalido. Corrija e informe um valor compativel com o tipo %s",
                path, ex.getValue(), ex.getTargetType());
        var problem = getProblemBuilder(status, problemType, details)
                .userMessage(MSG_GENERICO_ERRO_SISTEMA)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        var path = joinPath(ex.getPath());
        var problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        var details = String.format("A propriedade '%s' não existe. "
                + "Corrija ou remova essa propriedade e tente novamente.", path);
        var problem = getProblemBuilder(status, problemType, details)
                .userMessage(MSG_GENERICO_ERRO_SISTEMA)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handlerMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var problemType = ProblemType.PARAMETRO_INVALIDO;
        var details = String.format("O parâmetro de URL '%s' recebeu o valor '%s', que é de um tipo inválido. " +
                "Por favor, corrija e informe um valor compatível com o tipo '%s'.", ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
        var problem = getProblemBuilder(status, problemType, details)
                .userMessage(MSG_GENERICO_ERRO_SISTEMA)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private String joinPath(List<JsonMappingException.Reference> references) {
        return references.stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));
    }

    private Problem.ProblemBuilder getProblemBuilder(HttpStatusCode status, ProblemType problemType, String detail) {
        return Problem.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail);
    }
}
