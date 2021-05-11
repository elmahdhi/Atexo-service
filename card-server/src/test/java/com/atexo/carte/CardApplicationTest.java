package com.atexo.carte;

import com.atexo.carte.constant.ConstantField;
import com.atexo.carte.dto.CardDto;
import com.atexo.carte.dto.MainDto;
import com.atexo.carte.entity.Card;
import com.atexo.carte.exceptions.CardException;
import com.atexo.carte.service.ICardService;
import com.atexo.carte.service.impl.CardServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SpringBootTest
public class CardApplicationTest implements ConstantField {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardApplicationTest.class);

    private List<Card> cards = new ArrayList<>();
    private List<CardDto> mainCardsDtoSorted = new ArrayList<>();
    private List<CardDto> mainCardsDto = new ArrayList<>();
    private MainDto mainDto;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ICardService cardService;

    @Before
    public void initializer() throws CardException, IOException {
        /**
         * Initialize liste des cartes
         */
        Resource resource = new ClassPathResource("/static/cards.json");
        InputStream inputStream = resource.getInputStream();
        try {
            byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
            String data = new String(bdata, StandardCharsets.UTF_8);
            cards = mapper.readValue(data, mapper.getTypeFactory().constructCollectionType(List.class, Card.class));
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }

        /**
         * Initialize service mock
         */
        cardService = Mockito.mock(CardServiceImpl.class);

        List<CardDto> cardDtos = new ArrayList<>();
        for(int i = 0 ; i <= 9 ; i++){
            Card card = cards.get(i);
            cardDtos.add(new CardDto(card.getId(), card.getValue(), card.getColor(), card.getLibelle()));
        }
        mainDto = new MainDto(cardDtos);

        mainCardsDto = mainDto.getMainCards();
        mainCardsDtoSorted = mainDto.getMainCardsSorted();
        mainCardsDtoSorted.sort(Comparator.comparing(CardDto::getColor).thenComparing(CardDto::getValue));
    }

    @Test
    public void whenCardsIsCreatedThenReturnCards() throws CardException {
        Mockito.when(cardService.constructCards()).thenReturn(cards);
        Assert.notEmpty(cardService.constructCards(), "error cards");
        Assert.isTrue(cards.toArray().length == cardService.constructCards().toArray().length, "error cards");
        Assert.isTrue(cards.size() == cardService.constructCards().size(), "error cards");
    }

    @Test
    public void whenErrorInCreatedThenThrowException() throws CardException {
        Mockito.when(cardService.constructCards()).thenThrow(new CardException(createCardError));
        Assertions.assertThatThrownBy(() -> cardService.constructCards()).isInstanceOf(CardException.class);
    }

    @Test
    public void whenErrorInTransformDtoThenThrowException() throws CardException {
        Mockito.when(cardService.constructCards()).thenThrow(new CardException(transformError));
        Assertions.assertThatThrownBy(() -> cardService.constructCards()).isInstanceOf(CardException.class);
    }

    @Test
    public void whenMainCardIsCreatedThenReturnMainCards() throws CardException {
        Mockito.when(cardService.getRandomMainCards()).thenReturn(mainDto);
        Assert.notNull(cardService.getRandomMainCards(), "error mainDto");
        Assert.notEmpty(cardService.getRandomMainCards().getMainCards(), "error mainCards");
        Assert.notEmpty(cardService.getRandomMainCards().getMainCardsSorted(), "error mainCardsSorted");
        Assert.isTrue(cardService.getRandomMainCards().getMainCards().size() == mainDto.getMainCards().size(), "");
        Assert.isTrue(cardService.getRandomMainCards().getMainCardsSorted().size() == mainDto.getMainCardsSorted().size(),
                "error mainCardsSorted");
    }
}

