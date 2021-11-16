package ro.utcn.amqp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class Controller {



   @Autowired
    private MailService mailService;

    @GetMapping("/all")
    public ResponseEntity<List<NotificationRequestDTO>> test(){

        NotificationRequestDTO notificationRequestDTO = new NotificationRequestDTO("Ioana",15);
        NotificationRequestDTO notificationRequestDTO1 = new NotificationRequestDTO("Andrei",66);

        List<String> allADD = Arrays.asList("cluj","timisoara");
        notificationRequestDTO.setAddreess(allADD);

        List<NotificationRequestDTO> all = Arrays.asList(notificationRequestDTO1,notificationRequestDTO);

        System.out.println("am primit mesajul");
        return ResponseEntity.ok(all);
    }


    /**
     * 2XX
     * 200 - OK
     * 201 - CREATED
     *
     * 4XX
     * 404 - NOT FOUND
     *
     * 5XX
     * 500 - INTERNAL SERVER ERROR
     */
    @PostMapping("/create")
    public ResponseEntity<MessageDto> test2(@RequestBody NotificationRequestDTO notificationRequestDTO){
        System.err.println(notificationRequestDTO.toString());

        if(notificationRequestDTO.getId() == null || notificationRequestDTO.getId() == 6){
            MessageDto dto = new MessageDto();
            dto.setError("Error, not ok");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
        }

        MessageDto dto = new MessageDto();
        dto.setMessage("Successfully created new user");
        System.out.println("am primit ce vrei");
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }


    @PostMapping("/mail")
    public ResponseEntity<MessageDto> sendMail(@RequestBody UserDto userDto){
        System.out.println(userDto.getEmail());

        MessageDto dto = new MessageDto();
        dto.setMessage("Successfully sent mail");


        mailService.sendMail(userDto.getEmail(),"user created","Welcome to our platform!");



        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

}
