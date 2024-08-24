package az.edu.turing.fintechproproject.config;

import az.edu.turing.fintechproproject.mapper.UserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;

public class MapperConfig {
    @Bean
    public UserMapper userMapper() {
        return Mappers.getMapper(UserMapper.class);
    }

}
