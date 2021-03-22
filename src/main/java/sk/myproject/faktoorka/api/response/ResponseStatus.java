package sk.myproject.faktoorka.api.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ResponseStatus {

    SUCCESS(1),
    FAILED(2);

    private final Integer value;
}
