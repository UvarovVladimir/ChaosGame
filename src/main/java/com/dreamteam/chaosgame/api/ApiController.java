package com.dreamteam.chaosgame.api;

import com.dreamteam.chaosgame.db.ChaosGameRepository;
import com.dreamteam.chaosgame.db.Player;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {

    private final ChaosGameRepository chaosGameRepository;

    public ApiController(ChaosGameRepository chaosGameRepository) {
        this.chaosGameRepository = chaosGameRepository;
    }

//    @PostConstruct
//    void init() {
//        putInfo("");
//    }

    @GetMapping("/info/{infoId}")
    public ResponseDTO getInfo(@PathVariable("infoId") String infoId,
                               @RequestParam(name = "type", required = false) String type) {

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setInfo("Hello World!!!!!!!!!!!");
        responseDTO.setVersion("0.0.1");
        responseDTO.setInfoId(infoId);
        responseDTO.setType(type);

        return responseDTO;
    }

    @PostMapping("/info/{infoId}")
    public Player putInfo(@PathVariable("infoId") String infoId) {

        Player player = new Player();
        player.setAge(22);
        player.setLogin("sdfsdf");

        chaosGameRepository.save(player);

        return null;
    }
}
