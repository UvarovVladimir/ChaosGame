package com.dreamteam.chaosgame.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/info/{infoId}")
    public ResponseDTO getInfo(@PathVariable("infoId") String infoId,
                               @RequestParam(name = "type", required = false) String type,
                               @RequestParam(name = "value", required = false) int value) {

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setInfo("Hello World!!!!!!!!!!!");
        responseDTO.setVersion("0.0.1");
        responseDTO.setInfoId(infoId);
        responseDTO.setType(type);
        responseDTO.setValue(value);
        return responseDTO;
    }
}
