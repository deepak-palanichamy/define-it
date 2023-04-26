package dev.datapirate.defineit.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Data
public class DefinitionNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    private String message;
    private HttpStatus status;
}
