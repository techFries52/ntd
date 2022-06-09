package com.ntd.controllers;

import com.ntd.models.Bill;
import com.ntd.models.User;
import com.ntd.services.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class BillControllerTest extends AbstractTest {

    String uri = "/bill";
    int billId = 999;
    User testUser = new User();

    @Autowired
    UserService uServ;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {

    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {

    }

    @BeforeEach
    protected void setUp(){
        super.setUp();
    }

    @AfterEach
    void tearDown() throws Exception {

    }

    @Test
    @Order(2)
    @DisplayName("Save Bill to DB")
    void testSaveBill() throws Exception {
        int expectedStatus = 201;

        Bill bill = new Bill(billId, "testname", "TestCompany",9.99, new Date(System.currentTimeMillis()),false,testUser);
        String jsonBill = super.mapToJson(bill);

        MvcResult mvcRS = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonBill)).andReturn();

        int actualStatus = mvcRS.getResponse().getStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    @Order(3)
    @DisplayName("Save bill but it already exists")
    void testSaveBillAlreadyExist() throws Exception{
        int expectedStatus = 409;

        Bill bill = new Bill(billId, "testName", "TestCompany",9.99, new Date(System.currentTimeMillis()),false, testUser);
        String jsonBill = super.mapToJson(bill);

        MvcResult mvcRS = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonBill)).andReturn();

        int actualStatus = mvcRS.getResponse().getStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    @Order(4)
    @DisplayName("Update bill")
    void testUpdateBill() throws Exception {
        int expectedStatus = 200;

        Bill bill = new Bill(billId, "testName", "TestCompany",9.99, new Date(System.currentTimeMillis()),true, testUser);
        String jsonBill = super.mapToJson(bill);

        MvcResult mvcRS = mockMvc.perform(MockMvcRequestBuilders.put(uri + "/" + bill.getBillId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonBill)).andReturn();

        int actualStatus = mvcRS.getResponse().getStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    @Order(10)
    @DisplayName("Update bill that does not exist")
    void testUpdateBillDoesntExist() throws Exception {
        int expectedStatus = 404;

        Bill bill = new Bill(billId, "testName", "TestCompany",9.99, new Date(System.currentTimeMillis()),false, testUser);
        String jsonBill = super.mapToJson(bill);

        MvcResult mvcRS = mockMvc.perform(MockMvcRequestBuilders.put(uri+ "/" + bill.getBillId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonBill)).andReturn();

        int actualStatus = mvcRS.getResponse().getStatus();
        assertEquals(expectedStatus, actualStatus);
    }
    @Test
    @Order(5)
    @DisplayName("Get single bill")
    void testGetSingleBill() throws Exception {
        int expectedStatus = 200;

        Bill bill = new Bill(billId, "testName", "TestCompany",9.99, new Date(System.currentTimeMillis()),false, testUser);

        MvcResult mvcRS = mockMvc.perform(MockMvcRequestBuilders.get(uri + "/" + bill.getBillId())
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int actualStatus = mvcRS.getResponse().getStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    @Order(6)
    @DisplayName("Get all Bills")
    void testGetAllBills() throws Exception {
        int expectedStatus = 200;

        MvcResult mvcRS = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int actualStatus = mvcRS.getResponse().getStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    @Order(1)
    @DisplayName("Get All Bills when there is none")
    void testGetAllBillsEqualsZero() throws Exception {
        int expectedStatus = 204;

        MvcResult mvcRS = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int actualStatus = mvcRS.getResponse().getStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    @Order(9)
    @DisplayName("Get Bill that does not exist")
    void testGetBillThatDoesntExist() throws Exception {
        int expectedStatus = 404;

        Bill bill = new Bill(billId, "testName", "TestCompany",9.99, new Date(System.currentTimeMillis()),false, testUser);

        MvcResult mvcRS = mockMvc.perform(MockMvcRequestBuilders.get(uri + "/" + bill.getBillId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int actualStatus = mvcRS.getResponse().getStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    @Order(8)
    @DisplayName("Delete Bill")
    void testDeleteBill() throws Exception {
        int expectedStatus = 200;

        Bill bill = new Bill(billId, "testName", "TestCompany",9.99, new Date(System.currentTimeMillis()),false, testUser);

        MvcResult mvcRS = mockMvc.perform(MockMvcRequestBuilders.delete(uri + "/" + bill.getBillId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int actualStatus = mvcRS.getResponse().getStatus();
        assertEquals(expectedStatus, actualStatus);
    }
}
