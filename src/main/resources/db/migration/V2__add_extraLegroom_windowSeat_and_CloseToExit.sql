ALTER TABLE Seats
    ADD COLUMN extra_legroom BOOLEAN DEFAULT FALSE,
    ADD COLUMN close_to_exit BOOLEAN DEFAULT FALSE,
    ADD COLUMN window_seat BOOLEAN DEFAULT FALSE;
