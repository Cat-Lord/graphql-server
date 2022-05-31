INSERT INTO fish (name)
            VALUES ('Kapor'),
                   ('Štuka'),
                   ('Sumec'),
                   ('Zubáč'),
                   ('Ostriež'),
                   ('Pleskáč vysoký'),
                   ('Lieň sliznatý'),
                   ('Jalec hlavatý'),
                   ('Boleň'),
                   ('Amur'),
                   ('Karas'),
                   ('Úhor'),
                   ('Mieň'),
                   ('Tolstolobik'),
                   ('Mrena severná'),
                   ('Nosáľ'),
                   ('Iné druhy');

INSERT INTO fishing_ground(code, label)
       VALUES ('0010', 'Bobrie jazero'),
              ('0910', 'Štrkovisko Gajarské'),
              ('0911', 'Štrkovisko Suchohrad 1'),
              ('0912', 'Štrkovisko Suchohrad 2'),
              ('1470', 'Záhorský kanál'),
              ('0860', 'Špek'),
              ('0861', 'Kruh'),
              ('0862', 'Junkov Dolec'),
              ('0863', 'Habán'),
              ('0864', 'Strža'),
              ('0866', 'Štelc a Výkopa'),
              ('0480', 'Požiarna nádrž'),
              ('0410', 'Morava č. 3');

INSERT INTO attendance (id, number_of_visits, fishing_ground_id)
        VALUES (1,123,6),
               (2,168,2),
               (3,17,5);

INSERT INTO catch (id, total_amount, total_weight, attendance_id, fish_id)
        VALUES (1, 3, 5.0, 2, 2),
               (2, 1, 8.0, 2, 10),
               (3, 2, 6.0, 3, 1);