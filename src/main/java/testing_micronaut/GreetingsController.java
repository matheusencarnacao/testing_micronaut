package testing_micronaut;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;

@Controller("/greetings")
public class GreetingsController implements GreetingsControllerRoutes {

    @Value("${greeting.message}")
    private String greetings;

    @Get(uri="/")
    public HttpStatus index() {
        return HttpStatus.OK;
    }

    @Override
    public HttpResponse<String> greeting(String name) {
        return HttpResponse.ok(greetings + ", " + name + "!");
    }
}