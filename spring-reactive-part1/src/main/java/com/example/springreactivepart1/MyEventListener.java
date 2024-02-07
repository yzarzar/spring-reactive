package com.example.springreactivepart1;

import java.util.List;

public interface MyEventListener<T> {
    void onDataChunk(List<T> chunk);
    void processComplete();
}
