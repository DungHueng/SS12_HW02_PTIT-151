package com.example.bai_02.Controller;

import com.example.bai_02.Service.BannerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BannerController {

    private final BannerService bannerService;

    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @GetMapping("/banner")
    public String getBanner(
            @RequestParam String userId
    ) {
        return bannerService.getBanner(userId);
    }
}