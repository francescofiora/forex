package it.francescofiora.forex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Application starter.
 * 
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

  /**
   * Spring Boot starter.
   */
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(Application.class);
  }

  /**
   * main method.
   * 
   * @param args args
   */
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
