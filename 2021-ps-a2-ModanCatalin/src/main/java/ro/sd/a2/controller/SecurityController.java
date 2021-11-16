package ro.sd.a2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import ro.sd.a2.constants.*;
import ro.sd.a2.dto.MessageDto;
import ro.sd.a2.dto.UserDto;
import ro.sd.a2.service.UserService;

/**
 * Here add the basic security endpoints:login, register, default redirect if login success
 */
@Controller
public class SecurityController {

    private static final Logger log = LoggerFactory.getLogger(SecurityController.class);

    /**
     * @RequestMapping with htpp method type is a deprecated annotation.
     *
     * Use these annotations instead:
     *
     * @GetMapping - to perform a get request and retrieve pages with data.
     * @PostMapping - to perform an add request.
     * @PutMapping - to perform an update request.
     * @DeleteMapping - to perform an delete request.
     *
     */

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping(EndpointsRoutes.HOME)
    private ModelAndView home() {
        log.warn("Don't forget to use logs instead of system.out. And also log controllers/services for specific operations.");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        //log.info("Loged user doing this request is:" + userService.getSessionUserUsername());
        return modelAndView;
    }

    @GetMapping(EndpointsRoutes.LOGIN)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping(EndpointsRoutes.REGISTER)
    public ModelAndView register(UserDto userDto) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("userObj", userDto);
        mav.addObject("numeStudent", userDto.getName());

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        String result = userService.createAdmin(userDto);



        mav.setViewName("login");
        return mav;
    }

    @GetMapping(EndpointsRoutes.REGISTER)
    public ModelAndView getRegister(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("register");
        return mav;
    }
}
