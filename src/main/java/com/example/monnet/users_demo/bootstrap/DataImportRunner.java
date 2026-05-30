package com.example.monnet.users_demo.bootstrap;

import com.example.monnet.users_demo.service.UserImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataImportRunner implements ApplicationRunner {

    private final UserImportService userImportService;

    @Override
    public void run(ApplicationArguments args) {
        userImportService.importIfEmpty();
    }
}
