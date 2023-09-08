package uz.khurozov.jokeapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import uz.khurozov.jokeapi.constant.Category;
import uz.khurozov.jokeapi.constant.Lang;
import uz.khurozov.jokeapi.constant.Type;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Joke extends JokeBase {
    private Integer id;
    private Boolean safe;

    public Joke() {
    }

    public Joke(
            Category category,
            Type type,
            String joke,
            String setup,
            String delivery,
            Flags flags,
            Lang lang,
            Integer id,
            Boolean safe
    ) {
        super(category, type, joke, setup, delivery, flags, lang);
        this.id = id;
        this.safe = safe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getSafe() {
        return safe;
    }

    public void setSafe(Boolean safe) {
        this.safe = safe;
    }
}
