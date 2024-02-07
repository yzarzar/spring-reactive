package com.example.springreactivepart1;

import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HelloController {

    @GetMapping("/{name}")
    public Mono<ResponseEntity<ResponseDTO>> hello(@PathVariable("name") String name) {
        ResponseDTO dto = new ResponseDTO();
        dto.setName(name);
        return Mono.just(ResponseEntity.ok(dto));
    }

    @GetMapping("/flux")
    public Flux<String> fluxList() {
        Flux<String> helloFlux = Flux.just("1.foo ,", "2.bar ,", "3.foobar.");
        return helloFlux;
    }

    @GetMapping("/list")
    public Flux<String> list() {
        List<String> helloList = Arrays.asList("1.foo ","2.bar ,", "3.foobar.");
        Flux<String> seq2 = Flux.fromIterable(helloList);
        return seq2;
    }

    @GetMapping("/mono")
    public Mono<String> mono() {
        Mono<String> noData = Mono.just("Hello");
        return noData;
    }

    @GetMapping("/range")
    public Flux<Integer> helloRange() {
        Flux<Integer> numbersFromFiveToSeven = Flux.range(1, 10);
        return numbersFromFiveToSeven;
    }

    @GetMapping("/subscribe")
    public Flux<Integer> subscribe() {
        Flux<Integer> ints = Flux.range(1,10);
        ints.subscribe(i -> System.out.println(i));
        return ints;
    }

    @GetMapping("/error/{number}")
    public Flux<Integer> error(@PathVariable("number") int number) {
        Flux<Integer> ints = Flux.range(1,number)
                .map(i -> {
                    if (i <= 5) return i;
                    throw new RuntimeException("Go to 5");
                });
        ints.subscribe(i -> System.out.println(i),
        error -> System.err.println("Error : " + error));
        return ints;
    }

    @GetMapping("/completion")
    public Flux<Integer> completion() {
        Flux<Integer> ints = Flux.range(1,5);
        ints.subscribe(i -> System.out.println(i),
                err -> System.out.println("Error : " + err),
                () -> System.out.println("Done"));
        return ints;
    }

    @GetMapping("/sample-subscriber")
    public SampleSubscriber<Integer> smpSubscriber() {
        SampleSubscriber<Integer> ss = new SampleSubscriber<>();
        Flux<Integer> ints = Flux.range(1,5);
        ints.subscribe(ss);
        return ss;
    }

    @GetMapping("/cancel")
    public void cancel() {
        Flux.range(1, 10)
                .doOnRequest(r -> System.out.println("request of " + r))
                .subscribe(new BaseSubscriber<>() {

                    @Override
                    public void hookOnSubscribe(Subscription subscription) {
                        request(3);
                    }

                    @Override
                    public void hookOnNext(Integer integer) {
                        System.out.println("Cancelling after having received " + integer);
                        cancel();
                    }
                });
    }

    @GetMapping("/state-based")
    public Flux<String> stateBased() {
        Flux<String> flux = Flux.generate(
                () -> 0,
                (state, sink) -> {
                    sink.next("3 x " + (state) + " = " + (3 * state) + " / ");
                    if (state == 10) sink.complete();
                    return state + 1;
                }
        );
        return flux;
    }

    @GetMapping("/mutable")
    public Flux<String> mutable() {
        Flux<String> flux = Flux.generate(
                AtomicLong::new,
                (state, sink) -> {
                    long i = state.getAndIncrement();
                    sink.next("3 x " + i + " = " + (3 * i) + " / ");
                    if (i == 10) sink.complete();
                    return state;
                }
        );
        return flux;
    }

    @GetMapping("/generate")
    public Flux<String> generate() {
        Flux<String> flux = Flux.generate(
                AtomicLong::new,
                (state, sink) -> {
                    long i = state.getAndIncrement();
                    sink.next("3 x " + i + " = " + (3 * i) + " / ");
                    if (i == 10) sink.complete();
                    return state;
                }, (state) -> System.out.println("State : " + state)
        );
        return flux;
    }

    @GetMapping("/event-listener")
    public Flux<String> eventListener() {
        Flux<String> bridge = Flux.create(sink -> {
            new MyEventListener<String>() {

                @Override
                public void onDataChunk(List<String> chunk) {
                    for (String s : chunk) {
                        sink.next(s);
                    }
                }

                @Override
                public void processComplete() {
                    sink.complete();
                }
            };
        });
        return bridge;
    }

    @Autowired
    public void singleListener() {
         Flux.push(sink -> {
            new SingleThreadEventListener<String>() {
                @Override
                public void onDataChunk(List<String> chunk) {
                    for (String s : chunk) {
                        sink.next(s);
                    }
                }

                @Override
                public void processComplete() {
                    sink.complete();
                }

                @Override
                public void processError(Throwable e) {
                    sink.error(e);
                }
            };

        });
    }
}






















