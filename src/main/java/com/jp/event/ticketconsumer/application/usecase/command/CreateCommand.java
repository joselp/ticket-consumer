package com.jp.event.ticketconsumer.application.usecase.command;

import com.jp.event.ticketconsumer.application.domain.Ticket;
import com.jp.event.ticketconsumer.application.entity.TicketEntity;
import com.jp.event.ticketconsumer.application.usecase.TicketUseCase;

public class CreateCommand implements Command {

    private TicketUseCase ticketUseCase;
    private Ticket ticket;


    public CreateCommand(TicketUseCase ticketUseCase, Ticket ticket) {
        this.ticketUseCase = ticketUseCase;
        this.ticket = ticket;
    }

    @Override
    public Ticket execute() {
        return ticketUseCase.createTicket(ticket);
    }
}