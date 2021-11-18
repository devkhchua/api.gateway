package com.ms.resources.api.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/userfallback")
    public String userServiceFallbackMethod(){
        return "User service is taking longer than expected. " + "Please try again later.";
    }

    @GetMapping("/quotefallback")
    public String quoteServiceFallbackMethod(){
        return "Quotation service is taking longer than expected. " + "Please try again later.";
    }
}
