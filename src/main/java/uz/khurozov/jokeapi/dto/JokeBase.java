package uz.khurozov.jokeapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import uz.khurozov.jokeapi.constant.Category;
import uz.khurozov.jokeapi.constant.Lang;
import uz.khurozov.jokeapi.constant.Type;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JokeBase {
    private Category category;
    private Type type;
    private String joke;
    private String setup;
    private String delivery;
    private Flags flags;
    private Lang lang;

    public JokeBase() {
    }

    public JokeBase(
            Category category,
            Type type,
            String joke,
            String setup,
            String delivery,
            Flags flags,
            Lang lang
    ) {
        this.category = category;
        this.type = type;
        this.joke = joke;
        this.setup = setup;
        this.delivery = delivery;
        this.flags = flags;
        this.lang = lang;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public String getSetup() {
        return setup;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public Flags getFlags() {
        return flags;
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    public Lang getLang() {
        return lang;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
    }

    public void tellJoke() {
        if (type == Type.single) {
            System.out.println(joke);
        } else {
            System.out.println(setup);
            System.out.println(delivery);
        }
    }
}
