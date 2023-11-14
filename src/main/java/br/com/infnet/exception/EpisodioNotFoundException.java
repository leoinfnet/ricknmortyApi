package br.com.infnet.exception;

public class EpisodioNotFoundException extends RuntimeException {
    public EpisodioNotFoundException() {
    }

    public EpisodioNotFoundException(String message) {
        super(message);
    }
}
