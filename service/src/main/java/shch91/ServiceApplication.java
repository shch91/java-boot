package shch91;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import(value = {RepoApplication.class})
@ComponentScan(basePackages = "shch91.service")
public class ServiceApplication {

}
