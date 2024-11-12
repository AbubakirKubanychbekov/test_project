package kg.mega.test_project.exceptions;

public class BadCredentialException extends RuntimeException {
    public BadCredentialException(String s) {
        super(s);
    }
}