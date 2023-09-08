package uz.khurozov.jokeapi.exception;

import java.util.List;

public class JokeException extends RuntimeException{
    private final List<String> causedBy;
    private final String additionalInfo;

    public JokeException(String message, List<String> causedBy, String additionalInfo) {
        super(message);
        this.causedBy = causedBy;
        this.additionalInfo = additionalInfo;
    }

    public List<String> getCausedBy() {
        return causedBy;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }
}
