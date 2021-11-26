package com.luospace.geo.controller;

import com.luospace.geo.common.Result;
import com.luospace.geo.service.YilianyunService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/print")
public class PrintController {

    @Resource
    YilianyunService yilianyunService;

    @RequestMapping("/index")
    public Result index(){
        return Result.success(11111);
    }

    @RequestMapping("/test")
    public void test(){
        try {
            yilianyunService.printIndex();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
