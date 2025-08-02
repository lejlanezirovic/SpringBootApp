package SpringBootApp.App.general;

import lombok.Getter;
import lombok.Setter;
import java.time.Instant;

@Getter
@Setter
public class AuditDto {

    private Instant created;
    private Instant updated;
}
