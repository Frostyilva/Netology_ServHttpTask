package ru.netology;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Request {
    private final String path;
    private final List<NameValuePair> queryParams;

    public Request(String requestTarget) {

        try {
            var uri = new URI("http://localhost" + requestTarget);
            this.path = uri.getPath();
            var query = uri.getRawQuery();
            this.queryParams = (query != null)
                    ? URLEncodedUtils.parse(query, StandardCharsets.UTF_8)
                    : List.of();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPath() {
        return path;
    }

    public List<NameValuePair> getQueryParams() {
        return queryParams;
    }

    public String getQueryParam(String name) {
        return queryParams.stream()
                .filter(p -> p.getName().equals(name))
                .map(NameValuePair::getValue)
                .findFirst()
                .orElse(null);
    }
}
