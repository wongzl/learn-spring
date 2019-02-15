package com.wongzl.spring.hellospring;

import com.wongzl.spring.hellospring.config.CustomDuplicatedKeyException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloSpringApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test(expected = CustomDuplicatedKeyException.class)
	public void testThrowingCustomException() {
		jdbcTemplate.execute("INSERT INTO FOO (ID, BAR) VALUES (1, 'a')");
		jdbcTemplate.execute("INSERT INTO FOO (ID, BAR) VALUES (1, 'b')");
	}
}

