package com.sck.helpdesk;

import com.sck.helpdesk.domain.CategoryEntity;
import com.sck.helpdesk.domain.MessageEntity;
import com.sck.helpdesk.domain.TicketEntity;
import com.sck.helpdesk.domain.UserEntity;
import com.sck.helpdesk.repository.CategoryRepository;
import com.sck.helpdesk.repository.MessageRepository;
import com.sck.helpdesk.repository.TicketRepository;
import com.sck.helpdesk.repository.UserRepository;
import com.sck.helpdesk.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final MessageRepository messageRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public DataInitializer(CategoryRepository categoryRepository, MessageRepository messageRepository, TicketRepository ticketRepository, UserRepository userRepository, UserService userService) {
        this.categoryRepository = categoryRepository;
        this.messageRepository = messageRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        UserEntity agent = userService.createUser("sjones", "sjones123", UserEntity.UserType.AGENT);
        UserEntity customer = userService.createUser("bross", "bross123", UserEntity.UserType.CUSTOMER);

        CategoryEntity softwareCategory = new CategoryEntity("Software");
        categoryRepository.save(softwareCategory);

        CategoryEntity hardwareCategory = new CategoryEntity("Hardware");
        categoryRepository.save(hardwareCategory);

        TicketEntity someTicket = new TicketEntity(
                "Broken Keyboard",
                "I accidentally spilled my Capri Sun on my keyboard and now it won't work",
                customer
        );
        someTicket.setCategory(hardwareCategory);
        someTicket.setUserAssigned(agent);
        ticketRepository.save(someTicket);

        MessageEntity someMessage = new MessageEntity(
                "Hello, we will come by and swap out your keyboard soon",
                someTicket,
                agent
        );
        messageRepository.save(someMessage);

    }
}
