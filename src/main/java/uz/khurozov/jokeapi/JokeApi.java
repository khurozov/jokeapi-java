package uz.khurozov.jokeapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import uz.khurozov.jokeapi.constant.Category;
import uz.khurozov.jokeapi.constant.Flag;
import uz.khurozov.jokeapi.dto.Joke;
import uz.khurozov.jokeapi.dto.JokeFilter;
import uz.khurozov.jokeapi.dto.JokeResponse;
import uz.khurozov.jokeapi.exception.JokeException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.StringJoiner;
import java.util.logging.Logger;

public class JokeApi {
    private static final Logger log = Logger.getLogger(JokeApi.class.getName());
    private static final String JOKE_API_URL = "https://v2.jokeapi.dev";
    private static final ObjectMapper mapper = new ObjectMapper();

    public Joke getJoke(JokeFilter filter) throws IOException, InterruptedException {
        return get(filter, 1);
    }

    public List<Joke> getJokes(JokeFilter filter, int amount) throws IOException, InterruptedException {
        if (amount < 1) {
            log.warning("amount " + amount + " is setting back to minimum (1)");
            amount = 1;
        }
        if (amount > 10) {
            log.warning("amount " + amount + " is setting back to maximum (10)");
            amount = 10;
        }
        JokeResponse jokeResponse = get(filter, amount);

        return jokeResponse.getJokes() != null ? jokeResponse.getJokes() : List.of(jokeResponse);
    }

    private JokeResponse get(JokeFilter filter, int amount) throws IOException, InterruptedException {
        try {
            String category;

            if (filter == null || filter.categories() == null || filter.categories().isEmpty()) {
                category = Category.Any.name();
            } else {
                StringJoiner commaJoiner = new StringJoiner(",");
                for (Category c : filter.categories()) {
                    commaJoiner.add(c.name());
                }
                category = commaJoiner.toString();
            }

            URI uri = new URI(JOKE_API_URL + "/joke/" + category + query(filter, amount));
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            JokeResponse jokeResponse = mapper.readValue(response.body(), JokeResponse.class);

            if (jokeResponse.isError()) {
                throw new JokeException(jokeResponse.getMessage(), jokeResponse.getCausedBy(), jokeResponse.getAdditionalInfo());
            }

            return jokeResponse;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private String query(JokeFilter filter, int amount) {
        StringJoiner queryJoiner = new StringJoiner("&", "?", "");
        queryJoiner.setEmptyValue("");

        if (filter != null) {
            if (filter.blacklistFlags() != null && !filter.blacklistFlags().isEmpty()) {
                StringJoiner commaJoiner = new StringJoiner(",");
                for (Flag flag : filter.blacklistFlags()) {
                    commaJoiner.add(flag.name());
                }

                queryJoiner.add("blacklistFlags=" + commaJoiner);
            }

            if (filter.lang() != null) {
                queryJoiner.add("lang=" + filter.lang().name());
            }

            if (filter.idRange() != null) {
                queryJoiner.add("idRange=" + filter.idRange());
            }

            if (filter.contains() != null && !filter.contains().isBlank()) {
                queryJoiner.add("contains=" + URLEncoder.encode(filter.contains().trim(), StandardCharsets.UTF_8).replaceAll("\\+", "%20"));
            }

            if (filter.type() != null) {
                queryJoiner.add("type=" + filter.type());
            }
        }

        if (amount > 1) {
            queryJoiner.add("amount=" + amount);
        }
        return queryJoiner.toString();
    }
}
