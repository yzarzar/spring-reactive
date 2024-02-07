package com.example.springreactivepart1;

import java.util.List;

public interface SingleThreadEventListener<T> {
    void onDataChunk(List<String> chunk);
    void processComplete();
    void processError(Throwable e);
}
