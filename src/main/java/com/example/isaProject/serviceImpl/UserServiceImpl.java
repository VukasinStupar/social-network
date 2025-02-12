package com.example.isaProject.serviceImpl;

import com.example.isaProject.dto.UserDisplayDto;
import com.example.isaProject.dto.UserRequest;
import com.example.isaProject.dto.UserSearchDto;
import com.example.isaProject.model.Post;
import com.example.isaProject.model.Role;
import com.example.isaProject.model.User;
import com.example.isaProject.repository.FollowRepository;
import com.example.isaProject.repository.PostRepository;
import com.example.isaProject.repository.UserRepository;
import com.example.isaProject.service.RoleService;
import com.example.isaProject.service.UserService;
import com.example.isaProject.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private PostRepository postRepository;

    private TokenUtils tokenUtils;




    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public User findById(Long id) throws AccessDeniedException {
        return userRepository.findById(id).orElseGet(null);
    }

    public List<User> findAll() throws AccessDeniedException {
        return userRepository.findAll();
    }

    @Override
    public User save(UserRequest userRequest) {
        User u = new User();
        u.setName(userRequest.getName());
        u.setSurname(userRequest.getSurname());
        u.setUsername(userRequest.getUsername());
        u.setAdress(userRequest.getAdress());
        u.setEmail(userRequest.getEmail());
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        u.setEnabled(false);

        List<Role> roles = roleService.findByName("ROLE_USER");
        u.setRoles(roles);

        String activationToken = UUID.randomUUID().toString();
        u.setActivationToken(activationToken);

        u = userRepository.save(u);

        emailService.sendActivationCodeAndLink(u, activationToken);

        return u;

    }

    @Override
    public Long getPostNumber7Days(Long userId) {
        List<User> users = followRepository.allFollowersOfUser(userId);
        if(users.isEmpty()){
            return null;
        }
        Long numberOfPosts = 0L;

        for(User us : users){
            LocalDateTime timeLast7Days = LocalDateTime.now().minusDays(7);
            numberOfPosts += userRepository.numberOfPostsLast7Days(us.getId(), timeLast7Days);
        }
        return numberOfPosts;
    }
    //domaci
    public List<User> followers(User loggedUser){
        User user = userRepository.findById(loggedUser.getId()).orElse(null);
        if(user == null){
            return null;
        }
        return followRepository.allFollowersOfUser(user.getId());
    }

    //domaci
    public List<User> userFollow(User loggedUser){
        User user = userRepository.findById(loggedUser.getId()).orElse(null);
        if(user == null){
            return null;
        }
        return followRepository.allFollowOfUser(user.getId());
    }

    public Long countNewFollowersInLast7Days(Long userId) {
        LocalDateTime last7Days = LocalDateTime.now().minusDays(7);
        Long count = followRepository.countNewFollowersInLast7Days(userId, last7Days);

        return count;
    }

    @Override
    public User setLastLogin(Long userId){
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            return null;
        }
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
        return user;

    }

    @Override
    public void sendNotificationsToInactiveUsers() {
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);
        List<User> users = userRepository.findUsersLogged7DaysAgo(sevenDaysAgo);

        for(User user: users){
                Long postCount = userRepository.numberOfPostsLast7Days(user.getId(), sevenDaysAgo);
                Long followerCount = followRepository.countNewFollowersInLast7Days(user.getId(), sevenDaysAgo);

                emailService.sendWeeklyNotification(user, postCount, followerCount);
        }
    }

    @Override
    public List<UserDisplayDto> findUsersByAttributes(UserSearchDto userSearchDto) {
        int page = userSearchDto.getPage();
        int size = userSearchDto.getSize();

        Pageable pageable = PageRequest.of(page, size);

        List<User> users = userRepository.searchUsers(userSearchDto.getName(), userSearchDto.getSurname(),
                userSearchDto.getEmail(), userSearchDto.getPostFrom(), userSearchDto.getPostTo(),
                userSearchDto.getSortParam(), pageable);


        List<UserDisplayDto> userDisplayDtos = new ArrayList<>();

        for (User us : users) {

            UserDisplayDto udDto = new UserDisplayDto(us);
            userDisplayDtos.add(udDto);
        }
        return userDisplayDtos;
    }

    @Override
    public boolean activateUser(String token) {


        User user = userRepository.findUserByActivationToken(token);
        if(user == null){
            return false;
        }

        if (user.isEnabled()) {
            return false;
        }
        user.setEnabled(true);

        userRepository.save(user);
        return true;
    }

}