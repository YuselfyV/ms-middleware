package com.middleware.msmiddleware.configuration;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = "peticiones-topic", groupId = "mi-grupo")
    public void recibirPeticion(String mensaje) {
        // Procesar el mensaje recibido, por ejemplo, almacenar en un archivo de registro
        try {
            String filePath = "C:/Users/HP/Downloads/ms-middleware/src/main/java/com/middleware/msmiddleware/logs/log.txt";
            FileWriter fileWriter = new FileWriter(filePath, true);
            fileWriter.write(mensaje + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
