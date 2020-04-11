package com.sck.helpdesk.controller;

import com.sck.helpdesk.domain.MessageEntity;
import com.sck.helpdesk.domain.TicketEntity;
import com.sck.helpdesk.dto.MessageCreateForm;
import com.sck.helpdesk.dto.TicketCreateForm;
import com.sck.helpdesk.security.CurrentUserUtility;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/app/message")
public class MessageController {


}
