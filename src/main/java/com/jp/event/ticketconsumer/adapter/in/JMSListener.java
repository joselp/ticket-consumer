package com.jp.event.ticketconsumer.adapter.in;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jp.event.ticketconsumer.application.domain.Ticket;
import com.jp.event.ticketconsumer.application.port.in.TicketUseCase;
import com.jp.event.ticketconsumer.application.usecase.command.CommandExecutor;
import com.jp.event.ticketconsumer.application.usecase.command.CreateCommand;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Component
public class JMSListener {

    private CommandExecutor commandExecutor;
    private TicketUseCase ticketUseCase;

    public JMSListener(CommandExecutor commandExecutor, TicketUseCase ticketUseCase) {
        this.commandExecutor = commandExecutor;
        this.ticketUseCase = ticketUseCase;
    }

    @JmsListener(destination = "simpleJms.queue", containerFactory = "myFactory")
    public void receiveMessage(String message) throws JMSException, JsonProcessingException {
        System.out.println(message);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Ticket ticket = new ObjectMapper().setDateFormat(df).registerModule(new JavaTimeModule()).readValue(message, Ticket.class);
        System.out.println("Received <" + ticket + ">");

        commandExecutor.executeCommand(new CreateCommand(ticketUseCase, ticket));
    }
}