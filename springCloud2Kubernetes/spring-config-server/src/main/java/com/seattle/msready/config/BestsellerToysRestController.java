package com.seattle.msready.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Benjamin Wilms
 */
@RestController
@RequestMapping("/toys")
public class BestsellerToysRestController {

    private String appid = UUID.randomUUID().toString();

    @GetMapping("/bestseller")
    public List<String> getBestsellerProducts(HttpServletResponse response) {
        response.addHeader("appid", appid);
        AtomicLong aLong = new AtomicLong(1);

        return Arrays.asList("1", "2", "3");
    }

}
