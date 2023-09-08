JokeAPI Java
=====

Java wrapper for [JokeApi](https://v2.jokeapi.dev/) of [Sv443](https://sv443.net/)

## Installation

work in progress ...

## Usage example

``` java
JokeApi jokeApi = new JokeApi();

// Forming filter:

JokeFilter filter = new JokeFilter.Builder()
                .category(Category.Programming)
                .blacklistFlags(Set.of(Flag.nsfw, Flag.racist))
                .lang(Lang.en)
                .idRange(0, 305)
                .contains("Java")
                .type(Type.twopart)
                .build();

// Requesting joke(s):

Joke joke = jokeApi.getJoke(filter);
// or
List<Joke> jokes = jokeApi.getJokes(filter, 2);

// Printing joke:

System.out.println(joke.jokeString());
```