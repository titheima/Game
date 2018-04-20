package com.anoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;


/**
 * 企业级即时通信
 */
@SpringBootApplication
@EnableWebSocket
@EnableMongoHttpSession
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
