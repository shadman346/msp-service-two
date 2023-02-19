package com.example.servicetwo.controller;

import com.example.servicetwo.service.SqsOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/")
public class APIEndPoint {
    public final SqsOperation sqsOperation;

    public APIEndPoint(SqsOperation sqsOperation) {
        this.sqsOperation = sqsOperation;
    }

    @GetMapping("testS2")
    public String testEndpoint(){
        return "from service two";
    }

    @PostMapping("sqsMsg")
    public ResponseEntity<String> sendMessageToSqsQueue(@RequestBody String msgString){
        sqsOperation.sqsSendMessage(msgString);
        return ResponseEntity.status(HttpStatus.OK).body("message send to sqs!!");
    }

    @GetMapping("sqsMsg")
    public ResponseEntity<List<String>> sendMessageToSqsQueue(){
        return ResponseEntity.status(HttpStatus.OK).body(sqsOperation.sqsReceiveMessage());
    }
}
