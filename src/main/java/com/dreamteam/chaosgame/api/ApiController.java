package com.dreamteam.chaosgame.api;

import org.springframework.web.bind.annotation.*;

/**
 *
 */
@RestController
public class ApiController {

    /**
     * asdfadsf
     */
    private final int counter =5;

    /**
     * @param infoId
     * @param type
     * @return
     */
    @GetMapping("/info/{infoId}")
    public ResponseDTO getInfo(@PathVariable("infoId") String infoId,
                               @RequestParam(name = "type", required = false) String type) {

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setInfo("Hello World!!!!!!!!!!!");
        responseDTO.setVersion("0.0.1");
        responseDTO.setInfoId(infoId);
        responseDTO.setType(type);

        /*
        asdfasdf
         */

        return responseDTO;
    }
}
