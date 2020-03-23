package com.totoro.server.advertise.controller;

import com.totoro.common.response.Result;
import com.totoro.server.advertise.dto.DailyChoiceDTO;
import com.totoro.server.advertise.dto.DailySpecialDTO;
import com.totoro.server.advertise.service.AdvertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * AdvertiseController
 *
 * @author lwyang  2020/3/21
 */

@RestController
@RequestMapping("/ad")
public class AdvertiseController {

    @Autowired
    AdvertiseService advertiseService;

    @GetMapping("/dailySpecial")
    public Result<List<DailySpecialDTO>> listDailySpecial(){
        return Result.success(advertiseService.listDailySpecial());
    }

    @GetMapping("/dailyChoice")
    public Result<List<DailyChoiceDTO>> listDailyChoice(){
        return Result.success(advertiseService.listDailyChoice());
    }

}
