package com.jp.event.ticketconsumer.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Ticket implements Serializable {

    private String id;

    private LocalDateTime issueDate;
    private String seat;
    private LocalDateTime showDate;

    @JsonIgnore
    public boolean isValidTicket() {

        return showDate.isAfter(LocalDateTime.now());
    }
}