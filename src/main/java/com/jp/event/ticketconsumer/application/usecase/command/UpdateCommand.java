package com.jp.event.ticketconsumer.application.usecase.command;

import com.jp.event.ticketconsumer.application.domain.Ticket;
import com.jp.event.ticketconsumer.application.port.in.TicketUseCase;

public class UpdateCommand implements Command {

    private TicketUseCase ticketUseCase;
    private String id;
    private Integer days;


    public UpdateCommand(TicketUseCase ticketUseCase, String id, Integer days) {
        this.ticketUseCase = ticketUseCase;
        this.id = id;
        this.days = days;
    }

    @Override
    public Ticket execute() {
        return ticketUseCase.updateShowDate(id, days);
    }
}