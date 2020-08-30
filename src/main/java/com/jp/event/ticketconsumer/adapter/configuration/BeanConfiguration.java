package com.jp.event.ticketconsumer.adapter.configuration;

import com.jp.event.ticketconsumer.TicketConsumerApplication;
import com.jp.event.ticketconsumer.application.usecase.TicketUseCaseImpl;
import com.jp.event.ticketconsumer.application.port.in.TicketUseCase;
import com.jp.event.ticketconsumer.application.port.out.TicketRepository;
import com.jp.event.ticketconsumer.application.usecase.mapper.TicketMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = TicketConsumerApplication.class)
public class BeanConfiguration {

    @Bean
    TicketUseCase ticketUseCase(TicketRepository ticketRepository, TicketMapper ticketMapper){
        return new TicketUseCaseImpl(ticketRepository, ticketMapper);
    }
}