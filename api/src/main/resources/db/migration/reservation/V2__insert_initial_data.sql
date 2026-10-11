INSERT INTO reservation.guest (first_name, last_name, email) VALUES
    ('María',  'García',   'maria.garcia@example.com'),
    ('Javier', 'López',    'javier.lopez@example.com'),
    ('Elena',  'Martínez', 'elena.martinez@example.com');

INSERT INTO reservation.room_type_inventory (hotel_id, room_type_id, date, total_inventory, total_reserved) VALUES
    (1, 1, '2026-05-01', 10, 2),
    (1, 2, '2026-05-01',  5, 1),
    (2, 1, '2026-05-03',  8, 3);

INSERT INTO reservation.reservation (hotel_id, room_type_id, start_date, end_date, status, guest_id) VALUES
    (1, 1, '2026-05-01', '2026-05-04', 'CONFIRMED', 1),
    (1, 2, '2026-05-05', '2026-05-07', 'PENDING',   2),
    (2, 1, '2026-05-10', '2026-05-12', 'CANCELLED', 3);
