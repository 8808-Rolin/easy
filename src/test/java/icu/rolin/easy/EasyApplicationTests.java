package icu.rolin.easy;

import icu.rolin.easy.mapper.UserTableMapper;
import icu.rolin.easy.model.VO.AssListVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
		if (utm.findAll() == null){
			System.out.println("test");
		}else{
			System.out.println("no");
		}
	}

}
