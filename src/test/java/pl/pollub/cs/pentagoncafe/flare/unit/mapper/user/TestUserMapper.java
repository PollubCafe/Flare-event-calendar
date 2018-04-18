package pl.pollub.cs.pentagoncafe.flare.unit.mapper.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.UserResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.mapper.UserMapperImpl;
import pl.pollub.cs.pentagoncafe.flare.testObjectFactories.TestUserFactory;

@RunWith(MockitoJUnitRunner.class)
public class TestUserMapper {

    private UserMapperImpl userMapper;

    @Before
    public void setUp() {
        userMapper = new UserMapperImpl();
    }

    @Test
    public void whenIMapUserToResponseDTO_IGetUserResponseDTO() {

        //given
        User testUser = TestUserFactory.createTestUser();

        //when
        UserResponseDTO userResponseDTO = userMapper.mapToResponseDTO(testUser);

        //then
        Assert.assertEquals(testUser.getName(), userResponseDTO.getName());
        Assert.assertEquals(testUser.getSurname(), userResponseDTO.getSurname());
        Assert.assertEquals(testUser.getEmail(), userResponseDTO.getEmail());
        Assert.assertEquals(testUser.getPhoneNumber(), userResponseDTO.getPhoneNumber());
        Assert.assertEquals(testUser.getRole().toString(), userResponseDTO.getRole());
        Assert.assertEquals(testUser.getNick(), userResponseDTO.getNick());
    }
}
