package icu.rolin.easy;

import com.alibaba.fastjson.JSON;
import icu.rolin.easy.mapper.CollegeTableMapper;
import icu.rolin.easy.mapper.UserMapper;
import icu.rolin.easy.model.DO.College_Table;
import icu.rolin.easy.model.DO.User;
import icu.rolin.easy.model.VO.AssListVO;
import icu.rolin.easy.service.InfoService;
import icu.rolin.easy.service.ToolService;
import icu.rolin.easy.utils.common;
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

	}


}
