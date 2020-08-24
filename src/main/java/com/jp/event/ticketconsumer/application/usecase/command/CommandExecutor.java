package com.jp.event.ticketconsumer.application.usecase.command;

import org.springframework.stereotype.Component;

@Component
public class CommandExecutor {

    public void executeCommand(Command command) {
        command.execute();
    }
}
