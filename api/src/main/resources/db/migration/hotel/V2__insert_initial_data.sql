INSERT INTO hotel.hotel (name, address, latitude, longitude) VALUES
    ('Atenea',   'Calle Mayor 10, Madrid',              40.4168000, -3.7037900),
    ('Kontiki',  'Avenida del Mar 5, Barcelona',        41.3851000,  2.1734000),
    ('Apollo',   'Plaza España 2, Sevilla',             37.3891000, -5.9844600),
    ('Oleander', 'Paseo de la Castellana 45, Madrid',   40.4510000, -3.6900000);

INSERT INTO hotel.room (floor, number, hotel_id, name, is_available) VALUES
    (1, 101, 1, 'Doble Confort',  TRUE),
    (2, 202, 1, 'Suite Junior',   FALSE),
    (3, 305, 2, 'Doble Superior', TRUE),
    (1, 110, 3, 'Individual',     TRUE);

INSERT INTO hotel.room_type_rate (hotel_id, date, rate) VALUES
    (1, '2026-05-01',  85.00),
    (1, '2026-05-02',  92.50),
    (2, '2026-05-01', 120.00),
    (3, '2026-05-03',  75.00);
