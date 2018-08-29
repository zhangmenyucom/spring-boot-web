package com.taylor.common;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.function.Function;

/**
 * @author jearton
 * @since 2017/8/3 下午12:59
 */
@Slf4j
@UtilityClass
public class Retrofits {

    public <T> T execute(Call<T> call, Function<IOException, T> fallbackHandler) {
        try {
            return execute(call);
        } catch (IOException e) {
            return fallbackHandler.apply(e);
        }
    }

    public <T> T execute(Call<T> call) throws IOException {
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                T t = response.body();
                if (t == null) {
                    log.error("response body is null");
                    throw new IOException("response body is null");
                }
                return t;
            } else {
                log.error("response error, status = {}, message = {}", response.code(), response.message());
                throw new IOException("response error");
            }
        } catch (IOException e) {
            log.error("network error", e);
            throw new IOException("network error", e);
        }
    }
}
