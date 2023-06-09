package testing_micronaut;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;

public interface GreetingsControllerRoutes {

    @Get("/{name}")
    public HttpResponse<String> greeting(String name);
}
