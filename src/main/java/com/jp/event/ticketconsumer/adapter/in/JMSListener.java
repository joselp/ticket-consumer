package com.jp.event.ticketconsumer.adapter.in;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jp.event.ticketconsumer.application.domain.Ticket;
import com.jp.event.ticketconsumer.application.port.in.TicketUseCase;
import com.jp.event.ticketconsumer.application.usecase.command.CommandExecutor;
import com.jp.event.ticketconsumer.application.usecase.command.CreateCommand;
import com.jp.event.ticketconsumer.application.usecase.command.UpdateCommand;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JMSListener {

    private CommandExecutor commandExecutor;
    private TicketUseCase ticketUseCase;

    public JMSListener(CommandExecutor commandExecutor, TicketUseCase ticketUseCase) {
        this.commandExecutor = commandExecutor;
        this.ticketUseCase = ticketUseCase;
    }

    @JmsListener(destination = "simpleJms.queue", containerFactory = "myFactory")
    public void receiveMessage(Message message) throws JsonProcessingException, JMSException {

        if (message instanceof ActiveMQMapMessage) {
            Map<String, Object> messageObject = ((ActiveMQMapMessage) message).getContentMap();
            System.out.println(messageObject);

            messageObject.keySet().forEach(id ->
                commandExecutor.executeCommand(new UpdateCommand(ticketUseCase,
                        id, (int)messageObject.get(id))));

        } else {
            System.out.println(((ActiveMQTextMessage) message).getText());
            String messageString = ((ActiveMQTextMessage) message).getText();
            System.out.println(message);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Ticket ticket = new ObjectMapper().setDateFormat(df).registerModule(new JavaTimeModule()).readValue(messageString, Ticket.class);
            System.out.println("Received <" + ticket + ">");

            commandExecutor.executeCommand(new CreateCommand(ticketUseCase, ticket));
        }
    }

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    /*
     * Used for Receiving Message
     */
    @Bean
    public JmsListenerContainerFactory<?> jsaFactory(ConnectionFactory connectionFactory,
                                                     DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setMessageConverter(jacksonJmsMessageConverter());
        configurer.configure(factory, connectionFactory);
        return factory;
    }
}