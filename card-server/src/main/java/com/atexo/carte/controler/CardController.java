package com.atexo.carte.controler;

import com.atexo.carte.dto.MainDto;
import com.atexo.carte.exceptions.CardException;
import com.atexo.carte.response.Erreur;
import com.atexo.carte.response.Response;
import com.atexo.carte.service.ICardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services")
public class CardController {

    private static Logger LOGGER = LoggerFactory.getLogger(CardController.class);

    /**
     * Injection de service metier dans le controller
     */
    @Autowired
    private ICardService cardService;

    /**
     * Retourne une réponse qui contient 2 listes trié et non trié
     * L'ordre de tri est :
     *  Coouleur = Coeur, Pique, Carreaux, Trèfle
     *  Valuer = 2,3,4,5,6,7,8,9,10,AS,Dame,Roi, Valet
     * @return
     */
    @GetMapping(value = "/cards/main")
    public ResponseEntity<?> getMainCards(){
        MainDto mainDto;
        try {
            mainDto = cardService.getRandomMainCards();
            if(mainDto == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            if(mainDto.getMainCards().isEmpty() || mainDto.getMainCardsSorted().isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (CardException e){
            LOGGER.error(e.getMessage(), e);
            Response response = new Response();
            response.setError(new Erreur().message(e.getMessage()).code(HttpStatus.BAD_REQUEST.value()));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(mainDto, HttpStatus.OK);
    }
}
