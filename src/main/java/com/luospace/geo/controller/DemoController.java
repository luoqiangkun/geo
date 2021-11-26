package com.luospace.geo.controller;

import com.luospace.geo.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/a")
    public Result a(){
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return Result.success(ft.format(date));
    }

}
