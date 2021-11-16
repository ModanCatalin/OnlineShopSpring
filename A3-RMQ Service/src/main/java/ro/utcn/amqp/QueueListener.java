package ro.utcn.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueListener {

    @Autowired
    private MailService mailService;



    @RabbitListener(queues = "grupa8-queue")
    public void listen(String in) {
        System.out.println("Message read from myQueue : " + in);

        mailService.sendMail(in,"updated user","Your username has been updated!");

    }


    //@RabbitListener(queues = "grupa8-queue")
    //public  void listener(MessageDto messageDto)
    //{
      //  System.out.println(messageDto.getMessage());
    //}

}
