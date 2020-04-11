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

        UserEntity agent1 = userService.createUser("agent1", "agent123", UserEntity.UserType.AGENT);
        UserEntity agent2 = userService.createUser("agent2", "agent123", UserEntity.UserType.AGENT);
        UserEntity agent3 = userService.createUser("agent3", "agent123", UserEntity.UserType.AGENT);
        UserEntity customer1 = userService.createUser("cust1", "cust123", UserEntity.UserType.CUSTOMER);
        UserEntity customer2 = userService.createUser("cust2", "cust123", UserEntity.UserType.CUSTOMER);
        UserEntity customer3 = userService.createUser("cust3", "cust123", UserEntity.UserType.CUSTOMER);

        CategoryEntity softwareCategory = new CategoryEntity("Software");
        categoryRepository.save(softwareCategory);

        CategoryEntity hardwareCategory = new CategoryEntity("Hardware");
        categoryRepository.save(hardwareCategory);

        TicketEntity someTicket = new TicketEntity(
                "Broken Keyboard",
                "I accidentally spilled my Capri Sun on my keyboard and now it won't work",
                customer1,
                userService.retrieveAvailableAgent(),
                hardwareCategory
        );
        ticketRepository.save(someTicket);

        MessageEntity someMessage = new MessageEntity(
                "Hello, we will come by and swap out your keyboard soon",
                someTicket,
                someTicket.getUserAssigned()
        );
        messageRepository.save(someMessage);

        TicketEntity someTicket2 = new TicketEntity(
                "Page Won't Load",
                "The website isn't doing anything when I click it!",
                customer3,
                userService.retrieveAvailableAgent(),
                softwareCategory
        );
        ticketRepository.save(someTicket2);

    }
}
