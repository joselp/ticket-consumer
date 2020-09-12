package com.jp.event.ticketconsumer.application.port.out;

import com.jp.event.ticketconsumer.adapter.out.entity.TicketEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TicketRepository {

    TicketEntity create(TicketEntity ticketEntity);

    Optional<TicketEntity> get(String id);

    int updateShowDate(String id, LocalDateTime newDate);

    List<TicketEntity> getTickets();
}