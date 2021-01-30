package io.github.kimmking.gateway.homework;

import io.github.kimmking.gateway.filter.HttpResponseFilter;
import io.netty.handler.codec.http.FullHttpResponse;

public class HeaderHttpResponseFileFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("Content-Type", "application/download");
        response.headers().set("Content-Disposition", "attachment;filename=response.txt");
    }
}
