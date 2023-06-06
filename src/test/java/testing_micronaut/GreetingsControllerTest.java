package testing_micronaut;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import io.micronaut.http.client.annotation.*;
import jakarta.inject.Inject;
import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class GreetingsControllerTest {

    @Inject
    EmbeddedServer embeddedServer;

    @Inject
    @Client("/greetings")
    GreetingsControllerRoutes client;

    @Value("${greeting.message}")
    String greetingMessage;

    @Test
    public void testIndex() throws Exception {
        try(HttpClient client = embeddedServer.getApplicationContext().createBean(HttpClient.class, embeddedServer.getURL())) {
            assertEquals(HttpStatus.OK, client.toBlocking().exchange("/greetings").status());
        }
    }

    @Test
    public void greetingByNameTest(){
        HttpResponse<String> response = client.greeting("Matheus");
        String responseText = response.body();
        assertEquals(responseText, greetingMessage + ", Matheus!");

    }
}
