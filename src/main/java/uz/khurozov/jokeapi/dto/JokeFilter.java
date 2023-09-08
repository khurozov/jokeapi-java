package uz.khurozov.jokeapi.dto;

import uz.khurozov.jokeapi.constant.Category;
import uz.khurozov.jokeapi.constant.Flag;
import uz.khurozov.jokeapi.constant.Lang;
import uz.khurozov.jokeapi.constant.Type;

import java.util.Set;

public record JokeFilter(
        Set<Category> categories,
        Set<Flag> blacklistFlags,
        Lang lang,
        String idRange,
        String contains,
        Type type
) {
    public static class Builder {
        private Set<Category> categories;
        private Set<Flag> blacklistFlags;
        private Lang lang;

        private String idRange;
        private String contains;
        private Type type;

        public Builder categories(Set<Category> categories) {
            this.categories = categories;
            return this;
        }

        public Builder category(Category category) {
            this.categories = Set.of(category);
            return this;
        }

        public Builder blacklistFlags(Set<Flag> blacklistFlags) {
            this.blacklistFlags = blacklistFlags;
            return this;
        }

        public Builder lang(Lang lang) {
            this.lang = lang;
            return this;
        }

        public Builder id(int id) {
            this.idRange = Integer.toString(id);
            return this;
        }

        public Builder idRange(int from, int to) {
            this.idRange = from+"-"+to;
            return this;
        }

        public Builder contains(String contains) {
            this.contains = contains;
            return this;
        }

        public Builder type(Type type) {
            this.type = type;
            return this;
        }

        public JokeFilter build() {
            return new JokeFilter(categories, blacklistFlags, lang, idRange, contains, type);
        }
    }
}
