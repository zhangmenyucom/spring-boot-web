package com.taylor;

import com.taylor.service.MyCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author taylor
 */
@SpringBootApplication
public class StockApplicationStarter implements CommandLineRunner {

    @Autowired
    private MyCommandService commandService;

    public static void main(String... args) throws Exception {

        //不想看见 spring的logo
        SpringApplication app = new SpringApplication(StockApplicationStarter.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {

        if (args.length > 0 ) {
            System.out.println(commandService.getMessage(args[0]));
        }else{
            System.out.println(commandService.getMessage());
        }

        System.exit(0);
    }

}