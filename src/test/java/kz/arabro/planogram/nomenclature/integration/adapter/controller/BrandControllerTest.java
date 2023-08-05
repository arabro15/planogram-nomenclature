package kz.arabro.planogram.nomenclature.integration.adapter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.arabro.planogram.nomenclature.adapter.controller.response.CreateBrandResponse;
import kz.arabro.planogram.nomenclature.boundary.usecase.CreateBrandUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.DeleteBrandUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.ReadDataBrandUseCase;
import kz.arabro.planogram.nomenclature.boundary.usecase.UpdateBrandUseCase;
import kz.arabro.planogram.nomenclature.testdouble.controller.BrandRequestStub;
import kz.arabro.planogram.nomenclature.testdouble.entity.BrandStub;
import kz.arabro.planogram.nomenclature.util.annotation.IntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
class BrandControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private CreateBrandUseCase createBrandUseCase;

    @MockBean
    private DeleteBrandUseCase deleteBrandUseCase;

    @MockBean
    private UpdateBrandUseCase updateBrandUseCase;

    @MockBean
    private ReadDataBrandUseCase readDataBrandUseCase;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void createBrand_RequestIsNull_ReturnIsUnprocessableEntity() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/create-brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void createBrand_RequestIsValid_ReturnCreateBrandResponse() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        var createBrandRequest = BrandRequestStub.getCreateBrandRequest();

        var brand = BrandStub.getBrand();
        when(createBrandUseCase.execute(any())).thenReturn(brand);

        var createBrandResponse = new CreateBrandResponse();
        createBrandResponse.setBrandID(brand.getId().getValue().toString());

        mockMvc.perform(post("/api/v1/create-brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createBrandRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"brandID\":\"" + createBrandResponse.getBrandID() + "\"")));
    }

    @Test
    void deleteBrand_RequestIsInvalid_ReturnIsUnprocessableEntity() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/delete-by-id-brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void deleteBrand_RequestIsValid_ReturnIsStatusOk() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        var deleteBrandRequest = BrandRequestStub.getDeleteBrandRequest();

        doNothing().when(deleteBrandUseCase).deleteBrandByID(anyString());

        mockMvc.perform(post("/api/v1/delete-by-id-brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deleteBrandRequest)))
                .andExpect(status().isOk());
    }


}