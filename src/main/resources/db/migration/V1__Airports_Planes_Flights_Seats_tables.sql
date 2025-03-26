CREATE TABLE Airports (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          country VARCHAR(255) NOT NULL,
                          city VARCHAR(255) NOT NULL
);

CREATE TABLE Flights (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         startpoint_id INT NOT NULL,  -- Refers to Airports table (startpoint airport)
                         endpoint_id INT NOT NULL,    -- Refers to Airports table (endpoint airport)
                         price DECIMAL(10, 2) NOT NULL,
                         departure_time DATETIME NOT NULL,
                         arrival_time DATETIME NOT NULL,
                         FOREIGN KEY (startpoint_id) REFERENCES Airports(id),
                         FOREIGN KEY (endpoint_id) REFERENCES Airports(id)
);

CREATE TABLE Seats (
                       flight_id INT NOT NULL,           -- Refers to Flights table (specific flight)
                       seat_number INT NOT NULL,         -- Unique seat number for the flight
                       seat_class INT NOT NULL,          -- 1 for first class, 2 for business, 3 for economy
                       is_reserved BOOLEAN DEFAULT FALSE,  -- Whether the seat is reserved
                       PRIMARY KEY (flight_id, seat_number),  -- Composite primary key
                       FOREIGN KEY (flight_id) REFERENCES Flights(id) ON DELETE CASCADE
);
