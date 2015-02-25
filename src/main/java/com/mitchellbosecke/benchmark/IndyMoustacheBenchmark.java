package com.mitchellbosecke.benchmark;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.github.mustachejava.indy.IndyObjectHandler;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class IndyMoustacheBenchmark extends BaseBenchmark {

    private Map<String, Object> context;

    private Mustache template;

    @Setup
    public void setup() {
        DefaultMustacheFactory mustacheFactory = new DefaultMustacheFactory();
        mustacheFactory.setObjectHandler(new IndyObjectHandler());
        template = mustacheFactory.compile("templates/stocks.mustache.html");
        this.context = getContext();
    }

    @Benchmark
    public String benchmark() {
        Writer writer = new StringWriter();
        template.execute(writer, context);
        return writer.toString();
    }

}
