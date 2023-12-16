package com.example.yatchdice.exception.notfound;

import com.example.yatchdice.exception.ExceptionContext;

import static com.example.yatchdice.exception.ExceptionContext.NOT_FOUND_ROOM;

public class NotFoundRoomException extends NotFoundException{
    public NotFoundRoomException() {
        super(NOT_FOUND_ROOM);
    }
}
