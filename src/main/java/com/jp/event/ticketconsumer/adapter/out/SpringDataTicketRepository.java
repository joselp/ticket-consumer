package com.jp.event.ticketconsumer.adapter.out;

import com.jp.event.ticketconsumer.adapter.out.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Repository
public interface SpringDataTicketRepository extends JpaRepository<TicketEntity, String> {

    @Transactional
    @Modifying
    @Query("update TicketEntity t set t.showDate = ?2 where t.id = ?1")
    int updateShowDate(String id, LocalDateTime showDate);

}