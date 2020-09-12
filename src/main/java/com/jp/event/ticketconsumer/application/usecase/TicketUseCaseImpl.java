package com.jp.event.ticketconsumer.application.usecase;

import com.jp.event.ticketconsumer.adapter.mapper.TicketMapper;
import com.jp.event.ticketconsumer.adapter.out.entity.TicketEntity;
import com.jp.event.ticketconsumer.application.domain.Ticket;
import com.jp.event.ticketconsumer.application.port.in.TicketUseCase;
import com.jp.event.ticketconsumer.application.port.out.TicketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class TicketUseCaseImpl implements TicketUseCase {

    private TicketRepository ticketRepository;
    private TicketMapper ticketMapper;

    public TicketUseCaseImpl(TicketRepository ticketRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
    }

    @Override
    public Ticket createTicket(Ticket ticket) {

        TicketEntity entity = ticketRepository.create(TicketEntity.builder()
                .seat(ticket.getSeat())
                .issueDate(LocalDateTime.now())
                .showDate(ticket.getShowDate())
                .id(ticket.getId())
                .build());
        ticket.setId(entity.getId());
        ticket.setIssueDate(entity.getIssueDate());

        return ticket;
    }

    @Override
    public Optional<Ticket> getTicket(String id) {

        Optional<TicketEntity> ticketEntity = ticketRepository.get(id);

        return ticketEntity.map(entity -> Ticket.builder()
                .id(id)
                .issueDate(entity.getIssueDate())
                .seat(entity.getSeat())
                .showDate(entity.getShowDate())
                .build());
    }

    @Override
    public Ticket updateShowDate(String id, Integer postpone) {

        if (ticketRepository.updateShowDate(id, LocalDateTime.now().plusDays(postpone)) > 0) {
            return ticketMapper.ticketEntityToTicket(ticketRepository.get(id).get());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ticket not found");
    }

    @Override
    public List<Ticket> getTickets() {
        return ticketMapper.ticketEntityToTicket(ticketRepository.getTickets());
    }
}
