package com.example.servicetwo.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SqsOperation {
    public final AmazonSQS amazonSQS;
    @Value("${aws.sqs.url}")
    String sqsUrl;

    public SqsOperation(AmazonSQS amazonSQS) {
        this.amazonSQS = amazonSQS;
    }

    public void sqsSendMessage(String msg){
        // Set up the message to send
        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(sqsUrl)
                .withDelaySeconds(0)
                .withMessageBody(msg);

        // Send the message
        amazonSQS.sendMessage(sendMessageRequest);
    }
     public List<String> sqsReceiveMessage(){
        ReceiveMessageRequest messageRequest = new ReceiveMessageRequest(sqsUrl)
                .withWaitTimeSeconds(10)
                .withMaxNumberOfMessages(5);
        List<String> list = new ArrayList<>();
        ReceiveMessageResult queueResult = amazonSQS.receiveMessage(messageRequest);
        List<Message> messages = queueResult.getMessages();
        for (Message message : messages) {
            list.add(message.getBody() + " :::: " + message.getMessageId());
            log.info("Receive message from queue {}", message.getBody());
            // Delete the message from the queue
            String messageReceiptHandle = message.getReceiptHandle();
            amazonSQS.deleteMessage(new DeleteMessageRequest()
                    .withQueueUrl(sqsUrl)
                    .withReceiptHandle(messageReceiptHandle));
        }
        return list;
    }

}
