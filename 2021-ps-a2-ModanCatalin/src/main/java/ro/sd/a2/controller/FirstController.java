package ro.sd.a2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import ro.sd.a2.config.AMQPConfig;
import ro.sd.a2.config.RabbitSender;
import ro.sd.a2.dto.MessageDto;
import ro.sd.a2.dto.UserDto;
import ro.sd.a2.entity.User;
import ro.sd.a2.service.UserService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * This is user controller
 */
@Controller
@Transactional
public class FirstController
{
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private static final Logger log = LoggerFactory.getLogger(FirstController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitSender rabbitMQSender;


    @Autowired
    private RabbitSender rabbitSender;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/profile")
    public ModelAndView showProfile()
    {

        ModelAndView mav = new ModelAndView();

        User user = new User("Bubu");
        user.setEmail("user@email.com");

        mav.addObject("userObj", user);
        mav.addObject("numeStudent", user.getName());
        mav.addObject("email", user.getEmail());

        mav.setViewName("profile");

        return mav;
    }

    @GetMapping("/page")
    public ModelAndView page()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("page");

        rabbitMQSender.send("Hello gr 8");
/*
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        System.err.println(restTemplate.exchange("http://localhost:8080/user/all",
                HttpMethod.GET, entity, String.class).getBody());
*/

      /*  RestTemplate restTemplate = new RestTemplate();
        HttpEntity<UserDto> request = new HttpEntity<>(new UserDto("123","jean","jean@da"));
        ResponseEntity<MessageDto> response = restTemplate
                .exchange("http://localhost:8080/user/mail", HttpMethod.POST, request, MessageDto.class);

        MessageDto messageDto = response.getBody();
        System.out.println(messageDto.getMessage());

*/
        return mav;
    }






    @GetMapping("/test")
    public ModelAndView test()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("test");
        return mav;
    }

    /**
     * Ce face metoda?
     *
     * @param userDto - asta.....
     * @return .... MAV
     */
    @PostMapping("/addPerson")
    public ModelAndView addUser(UserDto userDto)
    {

        ModelAndView mav = new ModelAndView();
        mav.addObject("userObj", userDto);
        mav.addObject("numeStudent", userDto.getName());

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        String result = userService.createUser(userDto);


        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<UserDto> request = new HttpEntity<>(userDto);
        ResponseEntity<MessageDto> response = restTemplate
                .exchange("http://localhost:8080/user/mail", HttpMethod.POST, request, MessageDto.class);

        mav.addObject("result", result);

        mav.setViewName("home");
        return mav;
    }

    @PostMapping("/updatePerson")
    public ModelAndView updateUser(UserDto userDto)
    {
        log.info("received a request to update an user.");

        ModelAndView mav = new ModelAndView();
        mav.addObject("userObj1", userDto);
        mav.addObject("numeStudent1", userDto.getName());


        rabbitMQSender.send(userDto.getEmail());

        String result = userService.updateUser(userDto);

        mav.addObject("result", result);
        mav.setViewName("home");
        return mav;
    }

    @PostMapping("/deletePerson")
    public ModelAndView deleteUser(UserDto userDto)
    {
        log.info("received a request to update an user.");

        ModelAndView mav = new ModelAndView();
        mav.addObject("userObj1", userDto);
        mav.addObject("numeStudent1", userDto.getName());

        String result = userService.deleteUser(userDto);

        mav.addObject("result", result);

        mav.setViewName("home");
        return mav;
    }

    @GetMapping("/showPersons")
    public ModelAndView showUser()
    {
        ModelAndView mav = new ModelAndView();
        List<UserDto> userDtos = userService.getAllUsers();
        mav.addObject("users", userDtos);

        mav.setViewName("my-page");

        return mav;
    }

}
