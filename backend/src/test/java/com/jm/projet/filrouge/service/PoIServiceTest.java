package com.jm.projet.filrouge.service;

import com.jm.projet.filrouge.dto.PoIDTO;
import com.jm.projet.filrouge.mapper.PoIMapper;
import com.jm.projet.filrouge.model.PoI;
import com.jm.projet.filrouge.repository.PoIRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PoIServiceTest {

    @Mock
    PoIRepository poiRepo;

    @Mock
    PoIMapper poiMapper;

    private PoIService poiService;

    @BeforeEach
    public void setUp() throws Exception {
        poiService = new PoIService (poiRepo,poiMapper);
    }

    @Test
    public void findById() throws Exception {
      //  given (poiRepo.findById (1L )).willReturn (Optional.of(new PoI()));
        PoIDTO poiDTO = poiService.findById ( 1L);
        assertThat (poiDTO).isNull ( );
    }

    @Test
    public void findAll() throws Exception {
        given (poiRepo.findAll ( )).willReturn (new ArrayList<> ( ));
        List<PoIDTO> poiDTOs = poiService.findAll ( );
        assertThat (poiDTOs).isNotNull ( );
    }


}
