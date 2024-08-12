package com.qh.api;

import com.qh.application.ShortMessageSendCommand;
import com.qh.application.model.Result;
import com.qh.service.ShortMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jinx
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("sm")
public class ShortMessageController {

    private final ShortMessageService service;

    /**
     * 发送短信
     *
     * @param command 短信发送命令
     * @return void
     */
    @PostMapping("send")
    public Result<Void> send(@RequestBody ShortMessageSendCommand command) {
        return Result.ok(() -> service.send(command));
    }
}
