package com.jp.event.ticketconsumer.application.port.in;

import com.jp.event.ticketconsumer.application.entity.TicketEntity;

public interface TicketCommand {

    TicketEntity createTicket(TicketEntity ticketEntity);

    TicketEntity updateTicket(TicketEntity ticketEntity);
}
