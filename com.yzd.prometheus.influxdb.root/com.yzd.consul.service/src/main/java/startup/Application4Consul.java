package startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication

@ComponentScan({"com.yzd.consul.service"})
public class Application4Consul {

    public static void main(String[] args) {
        SpringApplication.run(Application4Consul.class, args);
    }
}