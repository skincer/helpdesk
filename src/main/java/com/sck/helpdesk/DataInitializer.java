package com.sck.helpdesk;

import com.sck.helpdesk.domain.CategoryEntity;
import com.sck.helpdesk.domain.MessageEntity;
import com.sck.helpdesk.domain.TicketEntity;
import com.sck.helpdesk.domain.UserEntity;
import com.sck.helpdesk.repository.CategoryRepository;
import com.sck.helpdesk.repository.MessageRepository;
import com.sck.helpdesk.repository.TicketRepository;
import com.sck.helpdesk.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final MessageRepository messageRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public DataInitializer(CategoryRepository categoryRepository, MessageRepository messageRepository, TicketRepository ticketRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.messageRepository = messageRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        UserEntity agent = new UserEntity("Sally", "Jones", UserEntity.UserType.AGENT);
        userRepository.save(agent);

        UserEntity customer = new UserEntity("Bob", "Ross", UserEntity.UserType.CUSTOMER);
        userRepository.save(customer);

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
                someTicket
        );
        messageRepository.save(someMessage);

    }
}
