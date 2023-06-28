package com.example.demo.resources;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
class ResourceLoadingComponent {
    @Value("classpath:/templates/header.html")
    Resource header;
    @Value("classpath:/templates/body.html")
    Resource body;
    @Value("classpath:/templates/footer.html")
    Resource footer;

}

@SpringBootTest(classes = {ResourceLoadingComponent.class})
class ResourceLoadingTest {
    @Autowired
    ResourceLoadingComponent component;

    @Test
    public void resourceLoadingTest(){
        var resources = List.of(component.header, component.body, component.footer);
        resources.stream().forEach(this::validateResource);
    }

    private void validateResource(Resource resource)  {
        try {
            Assertions.assertNotNull(resource);
            Assertions.assertNotNull(resource.getInputStream());
        } catch (IOException e) {
            Assertions.fail();
        }
    }
}