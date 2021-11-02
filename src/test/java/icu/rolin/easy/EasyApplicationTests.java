package icu.rolin.easy;

import icu.rolin.easy.model.VO.AssListVO;
import org.junit.jupiter.api.Test;
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

}
