package icu.rolin.easy;

import icu.rolin.easy.mapper.CollegeTableMapper;
import icu.rolin.easy.mapper.CommentsMapper;
import icu.rolin.easy.model.DO.Comments;
import icu.rolin.easy.utils.Common;
import icu.rolin.easy.utils.Constant;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class EasyApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private CollegeTableMapper collegeTableMapper;
	@Autowired
	private CommentsMapper commentsMapper;

	@Test
	void classTest(){
		for (String s : Common.getPathAllFileName(Constant.FILE_PATH)) {
			System.out.println(s);
		}
	}

	//测试字符串转时间戳
	@Test
	public void dateToStamp() throws Exception {
		String str = "2019-03-13 13:54:00";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = simpleDateFormat.parse(str);
		long ts = date.getTime();
		System.out.println(ts);

	}



}
