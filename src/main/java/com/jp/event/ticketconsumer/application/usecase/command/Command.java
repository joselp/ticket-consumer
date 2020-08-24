package com.jp.event.ticketconsumer.application.usecase.command;

import com.jp.event.ticketconsumer.application.domain.Ticket;

public interface Command {

    Ticket execute();
}
