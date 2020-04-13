package com.sck.helpdesk.service;

import com.sck.helpdesk.domain.CategoryEntity;
import com.sck.helpdesk.domain.MessageEntity;
import com.sck.helpdesk.domain.TicketEntity;
import com.sck.helpdesk.domain.UserEntity;
import com.sck.helpdesk.dto.SearchResult;
import com.sck.helpdesk.repository.CategoryRepository;
import com.sck.helpdesk.repository.MessageRepository;
import com.sck.helpdesk.repository.TicketRepository;
import com.sck.helpdesk.repository.UserRepository;
import com.sck.helpdesk.security.CurrentUserUtility;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.sck.helpdesk.security.Authorities.ROLE_AGENT;

@Service
public class SearchService {

    private final TicketRepository ticketRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public SearchService(TicketRepository ticketRepository, CategoryRepository categoryRepository, UserRepository userRepository, MessageRepository messageRepository) {
        this.ticketRepository = ticketRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    public List<SearchResult> performSearch(final String query) {

        List<SearchResult> results = new ArrayList<>();

        List<TicketEntity> tickets = new ArrayList<>();
        List<CategoryEntity> categories = new ArrayList<>();
        List<UserEntity> users = new ArrayList<>();
        List<MessageEntity> messages = new ArrayList<>();

        if(CurrentUserUtility.hasRole(ROLE_AGENT)) {

            tickets = ticketRepository.findFirst10ByTitleContainingIgnoreCaseOrderByIdDesc(query);
            categories = categoryRepository.findFirst10ByNameContainingIgnoreCaseOrderByIdDesc(query);
            users = userRepository.findFirst10ByUsernameContainingIgnoreCaseOrderByIdDesc(query);
            messages = messageRepository.findFirst10ByContentContainingIgnoreCaseOrderByIdDesc(query);

        } else {

            tickets = ticketRepository.findFirst10ByTitleContainingIgnoreCaseAndUserCreatedOrderByIdDesc(query, CurrentUserUtility.userEntity());

        }

        for(TicketEntity ticket : tickets) {
            results.add(new SearchResult(
                    ticket.getTitle(),
                    SearchResult.ResultType.TICKET,
                    "/app/ticket/" + ticket.getId()
            ));
        }

        for (CategoryEntity category : categories) {
            results.add(new SearchResult(
                    category.getName(),
                    SearchResult.ResultType.CATEGORY,
                    "/app/category/"+ category.getId()
            ));
        }

        for(UserEntity user : users) {
            results.add(new SearchResult(
                    user.getUsername(),
                    SearchResult.ResultType.USER,
                    "/app/user/" + user.getId()
            ));
        }

        for(MessageEntity message : messages) {
            results.add(new SearchResult(
                    message.getContent().substring(0, 20),
                    SearchResult.ResultType.MESSAGE,
                    "/app/ticket/"+ message.getTicket().getId()
            ));
        }

        return results;
    }

}
