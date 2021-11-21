package icu.rolin.easy;

import icu.rolin.easy.service.SelectService;
import icu.rolin.easy.utils.Common;
import icu.rolin.easy.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.io.File;

@EnableTransactionManagement
@SpringBootApplication
public class EasyApplication {
	private final static Logger logger = LoggerFactory.getLogger(EasyApplication.class);
	// 其中 dataSource 框架会自动为我们注入
	@Bean
	public PlatformTransactionManager txManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	public static void main(String[] args) {

		// 将资源名MD5写入到集合中
		logger.info("正在初始化文件列表......");
		File filepath = new File(Constant.FILE_PATH);
        // 判断路径是否存在，如果不存在就创建一个
        if (!filepath.exists()) {
            filepath.mkdirs();
        }
		for (String s : Common.getPathAllFileName(Constant.FILE_PATH)) {
			Common.FILE_MD5_LIST.add(s);
		}
		logger.info("写入完毕，文件列表长度为："+Common.FILE_MD5_LIST.size());


		SpringApplication.run(EasyApplication.class, args);
	}

}
