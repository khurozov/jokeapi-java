package uz.khurozov.jokeapi.dto;

public record Flags(
        boolean nsfw,
        boolean religious,
        boolean political,
        boolean racist,
        boolean sexist,
        boolean explicit
) {
    public Flags() {
        this(false, false, false, false, false, false);
    }
}
