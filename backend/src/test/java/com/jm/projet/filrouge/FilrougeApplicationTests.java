package com.jm.projet.filrouge;

import com.jm.projet.filrouge.dto.CityDTO;
import com.jm.projet.filrouge.dto.DepartmentDTO;
import com.jm.projet.filrouge.dto.RegionDTO;
import com.jm.projet.filrouge.model.Department;
import com.jm.projet.filrouge.model.Region;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@EnableAutoConfiguration
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FilrougeApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }


    // Test Region
    @Test
    public void region_FindAll() {
        final String URL = "/api/regions" ;
        final String RESULT = "Region Parisienne";

        ParameterizedTypeReference<List<RegionDTO>> typeRef = new ParameterizedTypeReference<List<RegionDTO>> () {};
        ResponseEntity<List<RegionDTO>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, new HttpEntity<> (null), typeRef);
        List<RegionDTO> objectList = responseEntity.getBody();
        RegionDTO object = objectList.get(0);
        assertThat(object.getName ()).isEqualTo (RESULT);
    }

    @Test
    public void region_FindById() {
        final String URL = "/api/regions/1" ;
        final String RESULT = "Region Parisienne";

        ParameterizedTypeReference<RegionDTO> typeRef = new ParameterizedTypeReference<RegionDTO> () {};
        ResponseEntity<RegionDTO> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, new HttpEntity<> (null), typeRef);
        RegionDTO object = responseEntity.getBody();
        assertThat(object.getName ()).isEqualTo (RESULT);
    }

/*
    // Test Department
    @Test
    public void department_FindAll() {
        final String URL = "/api/departments" ;
        final String RESULT = "Paris";

        ParameterizedTypeReference<List<RegionDTO>> typeRef = new ParameterizedTypeReference<List<RegionDTO>> () {};
        ResponseEntity<List<RegionDTO>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, new HttpEntity<> (null), typeRef);
        List<RegionDTO> objectList = responseEntity.getBody();
        RegionDTO object = objectList.get(0);
        assertThat(object.getName ()).isEqualTo (RESULT);
    }

    @Test
    public void department_FindById() {
        final String URL = "/api/departments/1" ;
        final String RESULT = "Paris";

        ParameterizedTypeReference<DepartmentDTO> typeRef = new ParameterizedTypeReference<DepartmentDTO> () {};
        ResponseEntity<DepartmentDTO> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, new HttpEntity<> (null), typeRef);
        DepartmentDTO object = responseEntity.getBody();
        assertThat(object.getName ()).isEqualTo (RESULT);
    }

    @Test
    public void department_findDepartmentsByRegionId() {
        final String URL = "/api/departments?idRegion=2" ;
        final String RESULT = "Saone et loire";

        ParameterizedTypeReference<List<DepartmentDTO>> typeRef = new ParameterizedTypeReference<List<DepartmentDTO>> () {};
        ResponseEntity<List<DepartmentDTO>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, new HttpEntity<> (null), typeRef);
        List<DepartmentDTO> objectList = responseEntity.getBody();
        DepartmentDTO object = objectList.get(0);
        assertThat(object.getName ()).isEqualTo (RESULT);
    }

    // Test City
    @Test
    public void city_FindAll() {
        final String URL = "/api/cities" ;
        final String RESULT = "Paris";

        ParameterizedTypeReference<List<CityDTO>> typeRef = new ParameterizedTypeReference<List<CityDTO>> () {};
        ResponseEntity<List<CityDTO>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, new HttpEntity<> (null), typeRef);
        List<CityDTO> objectList = responseEntity.getBody();
        CityDTO object = objectList.get(0);
        assertThat(object.getName ()).isEqualTo (RESULT);
    }

    @Test
    public void city_FindById() {
        final String URL = "/api/cities/1" ;
        final String RESULT = "Paris";

        ParameterizedTypeReference<CityDTO> typeRef = new ParameterizedTypeReference<CityDTO> () {};
        ResponseEntity<CityDTO> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, new HttpEntity<> (null), typeRef);
        CityDTO object = responseEntity.getBody();
        assertThat(object.getName ()).isEqualTo (RESULT);

    }

    @Test
    public void city_findDepartmentsByRegionId() {
        final String URL = "/api/cities?idDepartment=1" ;
        final String RESULT = "Paris";

        ParameterizedTypeReference<List<CityDTO>> typeRef = new ParameterizedTypeReference<List<CityDTO>> () {};
        ResponseEntity<List<CityDTO>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, new HttpEntity<> (null), typeRef);
        List<CityDTO> objectList = responseEntity.getBody();
        CityDTO object = objectList.get(0);
        assertThat(object.getName ()).isEqualTo (RESULT);
    }
*/

}

