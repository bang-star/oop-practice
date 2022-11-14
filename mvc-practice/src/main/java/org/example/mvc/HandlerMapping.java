package org.example.mvc;

public interface HandlerMapping {
    Object findHanlder(HandlerKey handlerKey);
}
