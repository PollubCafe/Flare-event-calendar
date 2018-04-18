package pl.pollub.cs.pentagoncafe.flare.unit.service.user;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;
import pl.pollub.cs.pentagoncafe.flare.domain.Participation;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.domain.enums.Role;
import pl.pollub.cs.pentagoncafe.flare.mapper.UserMapperImpl;
import pl.pollub.cs.pentagoncafe.flare.repository.event.EventRepository;
import pl.pollub.cs.pentagoncafe.flare.repository.user.UserRepository;
import pl.pollub.cs.pentagoncafe.flare.service.user.UserService;
import pl.pollub.cs.pentagoncafe.flare.service.user.UserServiceImpl;
import pl.pollub.cs.pentagoncafe.flare.testObjectFactories.TestEventFactory;
import pl.pollub.cs.pentagoncafe.flare.testObjectFactories.TestUserFactory;

import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapperImpl userMapper;
    @Mock
    private EventRepository eventRepository;

    private UserService userService;

    @Before
    public void setUp() {
        userService = new UserServiceImpl(userRepository, userMapper, eventRepository);
    }

    @Test
    public void whenIReadUserById_thenThisUserIsReadAndMapped() {

        //given
        User testUser = TestUserFactory.createTestUser();
        ObjectId testUserId = ObjectId.get();


        testUser.setId(testUserId);


        Mockito.when(userRepository.findById(testUserId)).thenReturn(Optional.of(testUser));

        //when
        userService.getUser(testUserId);

        //then
        Mockito.verify(userRepository, Mockito.times(1)).findById(testUserId);
        Mockito.verify(userMapper, Mockito.times(1)).mapToResponseDTO(testUser);

    }

    @Test
    public void whenIReadUserByNick_thenThisUserIsReadAndMapped() {

        //given
        User testUser = TestUserFactory.createTestUser();
        String testUserNick = "janek";


        testUser.setNick(testUserNick);


        Mockito.when(userRepository.findByNick(testUserNick)).thenReturn(Optional.of(testUser));

        //when
        userService.getUserByNick(testUserNick);

        //then
        Mockito.verify(userRepository, Mockito.times(1)).findByNick(testUserNick);
        Mockito.verify(userMapper, Mockito.times(1)).mapToResponseDTO(testUser);

    }

    @Test
    public void whenIReadAllUsersAssignedToEvent_thenTheseUsersAreReadAndMapped() {

        //given
        Event testEvent = TestEventFactory.createTestEvent();
        ObjectId testEventId = ObjectId.get();

        testEvent.setId(testEventId);


        Participation testUserParticipation = new Participation();
        testUserParticipation.setEvent(testEvent);
        testUserParticipation.setUser(TestUserFactory.createTestUser());


        Participation testUser1Participation = new Participation();
        testUserParticipation.setEvent(testEvent);
        testUserParticipation.setUser(TestUserFactory.createTestUser());

        testEvent.setParticipation(Sets.newHashSet(testUserParticipation, testUser1Participation));

        Mockito.when(eventRepository.findById(testEventId)).thenReturn(Optional.of(testEvent));

        //when
        userService.getAllUsersAssignedToEvent(testEventId);

        //then
        Mockito.verify(eventRepository, Mockito.times(1)).findById(testEventId);
        Mockito.verify(userMapper, Mockito.times(2)).mapToResponseDTO(Matchers.anyObject());

    }

    @Test
    public void whenIReadAllAdmins_thenTheseAdminsAreReadAndMapped() {
        //given
        List<User> testAdminsList = Lists.newArrayList();

        int adminsAmount = 3;

        for (int i = 0; i < adminsAmount; i++) {
            User admin = new User();
            admin.setRole(Role.ADMIN);
            testAdminsList.add(admin);
        }

        Mockito.when(userRepository.findAllByRoleIs(Role.ADMIN)).thenReturn(testAdminsList);

        //when
        userService.getAllAdmins();

        //then
        Mockito.verify(userRepository, Mockito.times(1)).findAllByRoleIs(Role.ADMIN);
        Mockito.verify(userMapper, Mockito.times(adminsAmount)).mapToResponseDTO(Matchers.anyObject());

    }

    @Test
    public void whenIReadAllUsers_thenTheseUsersAreReadAndMapped() {
        //given
        List<User> testUsers = Lists.newArrayList(TestUserFactory.createTestUser(),
                TestUserFactory.createTestUser(),
                TestUserFactory.createTestUser());

        Mockito.when(userRepository.findAll()).thenReturn(testUsers);

        //when
        userService.getAllUsers();

        //then
        Mockito.verify(userRepository, Mockito.times(1)).findAll();
        Mockito.verify(userMapper, Mockito.times(3)).mapToResponseDTO(Matchers.anyObject());

    }

    @Test
    public void whenIReadAllUnbannedUsers_thenTheseUsersAreReadAndMapped() {
        //given
        List<User> testUnbannedUsers = Lists.newArrayList();

        int unbannedUsersAmount = 3;

        for (int i = 0; i < unbannedUsersAmount; i++) {
            User unbannedUser = new User();
            unbannedUser.setBanned(false);
            testUnbannedUsers.add(unbannedUser);
        }

        Mockito.when(userRepository.findAllByBannedIsFalse()).thenReturn(testUnbannedUsers);

        //when
        userService.getAllUnBannedUsers();

        //then
        Mockito.verify(userRepository, Mockito.times(1)).findAllByBannedIsFalse();
        Mockito.verify(userMapper, Mockito.times(unbannedUsersAmount)).mapToResponseDTO(Matchers.anyObject());

    }
}
