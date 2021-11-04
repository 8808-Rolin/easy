package icu.rolin.easy;

import icu.rolin.easy.mapper.UserTableMapper;
import icu.rolin.easy.model.DO.User;
import icu.rolin.easy.model.VO.AssListVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class EasyApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void classTest(){
		AssListVO al = new AssListVO();
		al.setCode(200);
	}


	@Autowired
	UserTableMapper utm;
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
