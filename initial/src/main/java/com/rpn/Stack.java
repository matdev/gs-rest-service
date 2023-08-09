import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "app")
@Validated
public class Stack {

    private final int id;

    @ConstructorBinding
    public Stack(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
}
