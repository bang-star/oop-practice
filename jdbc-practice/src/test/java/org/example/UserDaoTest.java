package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {

    @BeforeEach     // 테스트 코드 실행 전에 수행해야할 작업
    void setUp() {

        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();

        // "db_schema.sql"파일을 ClassPathResource를 통해 읽어와서 스크립트 추가
        populator.addScript(new ClassPathResource("db_schema.sql"));
        // "db_schema.sql"파일의 Query 실행
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    void createTest() throws SQLException {
        // DAO 생성
        UserDao userDao = new UserDao();

        // DAO에 해당 USER 정보 저장을 요청
        userDao.create(new User("wizard", "password", "name", "email"));

        // DAO에 해당 아이디에 대한 정보 조회
        User user = userDao.findByUserId("wizard");

        // 검증
        assertThat(user).isEqualTo(new User("wizard", "password", "name", "email"));
    }
}
