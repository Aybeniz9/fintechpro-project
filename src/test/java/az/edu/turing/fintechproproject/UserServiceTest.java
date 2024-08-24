package az.edu.turing.fintechproproject;

import az.edu.turing.fintechproproject.dao.entity.UserEntity;
import az.edu.turing.fintechproproject.dao.repository.UserRepository;
import az.edu.turing.fintechproproject.mapper.UserMapper;
import az.edu.turing.fintechproproject.model.dto.UserDto;
import az.edu.turing.fintechproproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        UserEntity user1 = new UserEntity();
        UserEntity user2 = new UserEntity();
        UserDto userDto1 = new UserDto();
        UserDto userDto2 = new UserDto();

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
        when(userMapper.entityListToDtoList(Arrays.asList(user1, user2))).thenReturn(Arrays.asList(userDto1, userDto2));

        assertEquals(2, userService.findAll().size());
    }

    @Test
    void testFindById() {
        UserEntity userEntity = new UserEntity();
        UserDto userDto = new UserDto();

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
        when(userMapper.entityToDto(userEntity)).thenReturn(userDto);

        assertEquals(userDto, userService.findById(1L).get());
    }

    @Test
    void testCreate() {
        UserDto userDto = new UserDto();
        UserEntity userEntity = new UserEntity();

        when(userRepository.existsById(anyLong())).thenReturn(false);
        when(userMapper.dtoToEntity(userDto)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.entityToDto(userEntity)).thenReturn(userDto);

        assertEquals(userDto, userService.create(userDto));
    }

    @Test
    void testUpdate() {
        UserDto userDto = new UserDto();
        UserEntity userEntity = new UserEntity();

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.entityToDto(userEntity)).thenReturn(userDto);

        assertEquals(userDto, userService.update(1L, userDto));
    }

    @Test
    void testUpdatePassword() {
        UserDto userDto = new UserDto();
        UserEntity userEntity = new UserEntity();

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.entityToDto(userEntity)).thenReturn(userDto);

        assertEquals(userDto, userService.updatePassword(1L, "newPassword").get());
    }

    @Test
    void testDeleteById() {
        when(userRepository.existsById(anyLong())).thenReturn(true);

        userService.deleteById(1L);

        verify(userRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testDeleteAll() {
        when(userRepository.count()).thenReturn(1L);

        userService.deleteAll();

        verify(userRepository, times(1)).deleteAll();
    }
}