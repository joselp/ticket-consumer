package com.jp.event.ticketconsumer.adapter.mapper;

import com.jp.event.ticketconsumer.application.domain.Ticket;
import com.jp.event.ticketconsumer.adapter.out.entity.TicketEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    TicketEntity ticketToTicketEntity(Ticket ticket);

    Ticket ticketEntityToTicket(TicketEntity entity);

    List<Ticket> ticketEntityToTicket(List<TicketEntity> entities);
}