package icu.rolin.easy;

import icu.rolin.easy.mapper.CollegeTableMapper;
import icu.rolin.easy.mapper.CommentsMapper;
import icu.rolin.easy.mapper.PostMapper;
import icu.rolin.easy.model.DO.Comments;
import icu.rolin.easy.model.DO.Post;
import icu.rolin.easy.service.SelectService;
import icu.rolin.easy.utils.Common;
import icu.rolin.easy.utils.Constant;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class EasyApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private SelectService ss;
	@Autowired
	private CollegeTableMapper collegeTableMapper;
	@Autowired
	private CommentsMapper commentsMapper;
	@Autowired
	private PostMapper postMapper;


	@Test
	void classTest(){
		Calendar nowTime =Calendar.getInstance();
		nowTime.setTime(new Date());
		nowTime.set(Calendar.DATE,nowTime.get(Calendar.DATE)-7);
		Timestamp now = new Timestamp(System.currentTimeMillis());
		Timestamp week_before = new Timestamp(nowTime.getTimeInMillis());

		System.out.println(Common.convertTimestamp2Date(week_before,"yyyy-MM-dd"));
		for (Post post : postMapper.findByAidDate(3,week_before)) {
			System.out.println(post.getTitle());
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

	@Test
	public void count(){
		Random r = new Random();
		r.setSeed(System.currentTimeMillis());
		double a = 25; // 用户贴
		double b = 56; 	// 用户评论
		double c = 12; // 用户收藏
		a = Math.sqrt(100) * r.nextGaussian()+14;
		b = Math.sqrt(60) * r.nextGaussian() + 50;
		c = Math.sqrt(20) * r.nextGaussian() + 14;


		double max = 0;
		double min = 100000;

		double res = a * 0.5 + b * 0.4 + c * 0.1;
		System.out.println("Beginning....");
		int e = 0;
		double f = 0;
		double g = 0;
		for (int i = 0; i < 10000000; i++) {
			a = Math.sqrt(144) * r.nextGaussian() + 12;
			if (a < 0) a = 0;

			b =Math.sqrt(100) * r.nextGaussian() + 35;
			if(b < 0) b = 0;

			c = Math.sqrt(20) * r.nextGaussian() + 14;
			if (c < 0) c = 0;

			double x = a * 0.5 + b * 0.4 + c * 0.1;
			if (x > max) max = x;
			if (x < min) min = x;
//			System.out.println(x);
			res= (res + x)/2.0;

			e++;
			f += res;
			if (e == 100){
				g = f / 100;
				f = 0;
				e=0;
				System.out.println("------>g:"+g);
			}
		}

		System.out.println("max:"+max);
		System.out.println("min:"+min);
		System.out.println("avg:"+res);

	}


}
