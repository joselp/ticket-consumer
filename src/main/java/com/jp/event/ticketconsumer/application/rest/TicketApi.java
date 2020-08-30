package com.jp.event.ticketconsumer.application.rest;

import com.jp.event.ticketconsumer.application.domain.Ticket;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface TicketApi {

    @GetMapping("/tickets")
    public List<Ticket> getTickets();
}
