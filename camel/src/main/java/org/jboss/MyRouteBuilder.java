package org.jboss;

import org.apache.camel.builder.RouteBuilder;

import java.io.IOException;

public class MyRouteBuilder extends RouteBuilder {

    public void configure() throws IOException {

        BeanMessageISO8583 isoBean = new BeanMessageISO8583();
        isoBean.init();

        from("file:src/data/iso8583?noop=true")
           .convertBodyTo(String.class)
           .bean(isoBean, "marshall")
           .log("ISO : ${body}");
    }

}
