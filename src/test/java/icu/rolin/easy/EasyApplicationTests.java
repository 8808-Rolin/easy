package icu.rolin.easy;

import com.alibaba.fastjson.JSON;
import icu.rolin.easy.mapper.CollegeTableMapper;
import icu.rolin.easy.mapper.UserMapper;
import icu.rolin.easy.model.DO.College_Table;
import icu.rolin.easy.model.DO.User;
import icu.rolin.easy.model.VO.AssListVO;
import icu.rolin.easy.service.InfoService;
import icu.rolin.easy.service.ToolService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;

@SpringBootTest
class EasyApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private ToolService toolService;
	@Autowired
	private CollegeTableMapper collegeTableMapper;
	@Test
	void classTest(){
		for (College_Table college_table : collegeTableMapper.findAll()) {
			System.out.println(JSON.toJSONString(college_table));
		}

	}


	@Autowired
    UserMapper utm;
	@Test
	void jdbcTest(){
		System.out.println(JSON.toJSONString(toolService.getCollegeList()));
	}

	@Test
	void exp(){
		try {
			int a=1;
			Integer b =null;
			int c = 1/0;
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("e.getMessage="+e.getMessage());
			System.out.println("e="+e.toString());
			for (StackTraceElement stackTraceElement : e.getStackTrace()) {
				System.out.println("e="+stackTraceElement.toString());
			}

		}
	}
	@Test
	void Simple(){

	}

}
