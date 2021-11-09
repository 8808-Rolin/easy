package icu.rolin.easy;

import icu.rolin.easy.mapper.CollegeTableMapper;
import icu.rolin.easy.mapper.CommentsMapper;
import icu.rolin.easy.model.DO.Comments;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

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
		Comments comments = commentsMapper.getLastCommentWithPost(100);
		System.out.println(comments);
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
