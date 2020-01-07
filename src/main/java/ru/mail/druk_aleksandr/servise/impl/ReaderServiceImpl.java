package ru.mail.druk_aleksandr.servise.impl;

import ru.mail.druk_aleksandr.servise.ReaderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

public class ReaderServiceImpl implements ReaderService {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void reader() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("JD2.txt"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                logger.info(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }
}
