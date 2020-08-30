package com.jp.event.ticketconsumer.application.port.in;

import com.jp.event.ticketconsumer.application.domain.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketUseCase {

    Ticket createTicket(Ticket ticket);

    Optional<Ticket> getTicket(String id);

    List<Ticket> getTickets();

    boolean updateShowDate(String id, Integer postpone);
}
