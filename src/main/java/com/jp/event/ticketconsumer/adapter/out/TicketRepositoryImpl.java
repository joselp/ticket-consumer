package com.jp.event.ticketconsumer.adapter.out;

import com.jp.event.ticketconsumer.application.entity.TicketEntity;
import com.jp.event.ticketconsumer.application.port.out.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Primary
public class TicketRepositoryImpl implements TicketRepository {

    private SpringDataTicketRepository repository;

    @Autowired
    public TicketRepositoryImpl(SpringDataTicketRepository repository) {
        this.repository = repository;
    }

    @Override
    public TicketEntity create(TicketEntity ticketEntity) {
        return repository.save(ticketEntity);
    }

    @Override
    public Optional<TicketEntity> get(String id) {
        return repository.findById(id);
    }

    @Override
    public int updateShowDate(String id, LocalDateTime newDate) {
        return repository.updateShowDate(id, newDate);
    }

    @Override
    public List<TicketEntity> getTickets() {
        return repository.findAll();
    }
}
