package com.chessknight.chessknight;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ChessknightApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ChessknightApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        TerminalApplication.mainTerminalLoop();
    }

}
