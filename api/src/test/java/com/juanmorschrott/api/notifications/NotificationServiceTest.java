package com.juanmorschrott.api.notifications;

import com.juanmorschrott.api.hotel.HotelCreatedEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.test.ApplicationModuleTest;

@ApplicationModuleTest
class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;

    @Test
    void shouldHandleEvent() {
        HotelCreatedEvent event = new HotelCreatedEvent(1L, "Test Hotel");
        
        // As the listener is synchronous inside its thread/transaction (or directly executed in a unit test),
        // we can simply invoke it or rely on ApplicationModuleTest to publish the event. 
        // For now, testing directly the `on` method if it was visible, but since it's package-private, 
        // we can call it within the same package.
        
        // We will call it to ensure it executes without errors.
        notificationService.on(event);
    }
}
