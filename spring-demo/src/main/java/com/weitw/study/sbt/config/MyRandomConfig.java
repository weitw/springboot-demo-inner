package com.weitw.study.sbt.config;

import com.weitw.study.sbt.domain.Address;
import com.weitw.study.sbt.domain.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "my-random")
public class MyRandomConfig {
    private String secret;
    private Integer number;
    private Long bigNumber;
    private String uuid;
    private Integer numberLessThanTen;
    @Value("${my-random.number-in-range}")
    private Integer numberBefore;
    private List<Integer> list1;
    private List<Integer> list2;
    private Map<String, String> mapinfo;
    private Address address;
    private List<User> userList;

}
