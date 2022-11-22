package com.lai.order.config;

import javafx.scene.image.Image;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;



@Configuration
@MapperScan("com.lai.order.mapper")
public class OrderConfig {

}
