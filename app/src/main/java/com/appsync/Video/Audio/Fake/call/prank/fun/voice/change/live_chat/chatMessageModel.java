package com.appsync.Video.Audio.Fake.call.prank.fun.voice.change.live_chat;

public class chatMessageModel {
    private String message;
    private boolean userMessage;

    public chatMessageModel(String message, boolean userMessage) {
        this.message = message;
        this.userMessage = userMessage;
    }

    public String getMessage() {
        return message;
    }

    public boolean isUserMessage() {
        return userMessage;
    }
}

