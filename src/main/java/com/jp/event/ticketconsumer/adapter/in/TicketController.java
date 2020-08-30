package com.jp.event.ticketconsumer.adapter.in;

import com.jp.event.ticketconsumer.application.domain.Ticket;
import com.jp.event.ticketconsumer.application.rest.TicketApi;
import com.jp.event.ticketconsumer.application.port.in.TicketUseCase;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TicketController implements TicketApi {

    private TicketUseCase ticketUseCase;

    public TicketController(TicketUseCase ticketUseCase) {
        this.ticketUseCase = ticketUseCase;
    }

    @Override
    public List<Ticket> getTickets() {
        return ticketUseCase.getTickets();
    }
}