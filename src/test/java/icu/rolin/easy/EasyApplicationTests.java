package icu.rolin.easy;

import icu.rolin.easy.mapper.UserMapper;
import icu.rolin.easy.model.DO.User;
import icu.rolin.easy.model.VO.AssListVO;
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

	@Test
	void classTest(){
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(10);
		config.setMaxIdle(8);
		JedisPool jedisPool = new JedisPool(config,"easy.30202.co",6379);

	}


	@Autowired
    UserMapper utm;
	@Test
	void jdbcTest(){
		ArrayList<User> users = utm.findAll();
		if (users == null){
			System.out.println("no");
		}else{
			for (User user : users) {
				System.out.println(user.toString());
			}
		}
	}

}
