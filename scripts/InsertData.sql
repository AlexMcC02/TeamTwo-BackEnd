INSERT INTO Capability (ID, Name) VALUES
('1', 'Engineering'),
('2', 'Platforms'),
('3', 'Data & Artificial Intelligence'),
('4', 'Cyber Security'),
('5', 'Workday'),
('6', 'Experience Design'),
('7', 'Product'),
('8', 'Delivery'),
('9', 'Operations'),
('10', 'Business Development & Marketing'),
('11', 'Organisational Strategy & Planning'),
('12', 'People'),
('13', 'Commercial & Financial Management'),
('14', 'Business Service Support');

INSERT INTO BandLevel (ID, Name) VALUES
('1', 'Apprentice'),
('2', 'Trainee'),
('3', 'Associate'),
('4', 'Senior Associate'),
('5', 'Consultant'),
('6', 'Manager'),
('7', 'Principal');

INSERT INTO `JobRole` (`Name`, `Specification`, `BandID`, `CapabilityID`, `UrlLink`) VALUES
('Bond James Bond', 'Updated Job Specification', '1', '1', 'https://google.com'),
('Joris Bohnson', 'Updated Job Specification', '2', '8', 'https://google.com'),
('Tonald Drump', 'Updated Job Specification', '1', '3', 'https://google.com'),
('Captain Kirk', 'Updated Job Specification', '5', '11', 'https://google.com'),
('Harry Potter', 'Updated Job Specification', '3', '12', 'https://google.com'),
('Captain Picard', 'Updated Job Specification', '2', '5', 'https://google.com'),
('Samuel', 'Updated Job Specification', '4', '14', 'https://google.com'),
('Michael', 'Updated Job Specification', '6', '13', 'https://google.com'),
('Andrew', 'Updated Job Specification', '7', '12', 'https://google.com'),
('Lyanna', 'Updated Job Specification', '1', '3', 'https://google.com'),
('Alice', 'Updated Job Specification', '2', '4', 'https://google.com');