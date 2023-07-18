INSERT INTO Capability (ID, Name) VALUES ('1', 'Engineering');

INSERT INTO Capability (ID, Name) VALUES ('2', 'Digital Services');

INSERT INTO BandLevel (ID, Name) VALUES ('1', 'Manager');

INSERT INTO BandLevel (ID, Name) VALUES ('2', 'Trainee');

INSERT INTO JobRole (ID ,Name ,Specification ,BandLevelID, CapabilityID) VALUES ('1', 'Joe Bloggs', 'Big Lad', 1, 1);

INSERT INTO JobRole (ID ,Name ,Specification ,BandLevelID, CapabilityID) VALUES ('2', 'Philip Wilson', 'Test Engineer', 1, 2);