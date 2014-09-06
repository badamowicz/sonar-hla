package com.github.badamowicz.sonar.hla.exceptions;

/**
 * Exceptions of this type are thrown if some error occurred while processing data from SonarQube.
 */
public class SonarProcessingException extends RuntimeException {

    private static final long serialVersionUID = -7278257021021061090L;

    public SonarProcessingException() {

        super();
    }

    public SonarProcessingException(String message) {

        super(message);
    }

    public SonarProcessingException(Throwable cause) {

        super(cause);
    }

    public SonarProcessingException(String message, Throwable cause) {

        super(message, cause);
    }

}
