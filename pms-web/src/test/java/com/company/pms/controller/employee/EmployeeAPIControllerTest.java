package com.company.pms.controller.employee;

import com.company.pms.pmsbase.utils.JsonUtil;
import com.company.pms.pmsrepository.employee.domain.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * @Author zcf
 * @Create 2018/7/27 22:47
 * @Desc
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureWebMvc
public class EmployeeAPIControllerTest {

    @Autowired
    private WebApplicationContext context;

    private static MockMvc mvc;

    @Before
    public void setupMockMvc() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void test() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/api/employee?page=1&limit=10")).andReturn();
        System.out.println("======================================================");
        System.out.println("status: " + result.getResponse().getStatus());
        String content = JsonUtil.getJsonValue(result.getResponse().getContentAsString(),"content");
        System.out.println(content);
        List<Employee> list = JsonUtil.jsonToList(content, Employee.class);
        for(Employee employee : list){
            System.out.println("id: " + employee.getId() + " name: " + employee.getName());
        }
        System.out.println("======================================================");
        /**
         * perform()  用于构造一个URL请求
         * MockMvcRequestBuilders.get(String urlTemplate)  get请求(还有post, delete, update)
         * andReturn()  得到请求返回的结果
         * MvcResult 请求返回的结果
         * result.getResponse().getStatus()  得到返回的Http状态码，如(200,400,500)
         * result.getResponse().getContentAsString()); 得到返回的Json字符串
         * JsonUtil.jsonToList()  将Json字符串解析成对应对象的数组
         */
    }

}