package com.wiryaimd.r_services1.controller;

import com.wiryaimd.r_services1.config.AppConfiguration;
import com.wiryaimd.r_services1.model.LimitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class My1Controller {

    @Autowired
    private AppConfiguration appConfiguration;

    @GetMapping("/limits")
    public ResponseEntity<LimitModel> get1(){
        LimitModel limitModel = new LimitModel(
                234,
                24
        );

        String mengontol = "hemm hooh yea";
        System.out.println(mengontol + " dengan limit: " + limitModel.getMinimum());

        return ResponseEntity.ok(new LimitModel(appConfiguration.getMinimum(), appConfiguration.getMaximum()));
    }

}
