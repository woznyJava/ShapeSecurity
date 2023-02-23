-- # # --for the password using online bcrypt encoder, encode your password
-- # # --and then use it here prefixed with {bcrypt}

INSERT INTO APP_USER(id, email,first_name, last_name, password, role)
 VALUES (100001, 'jan.kowalski@gmail.com', 'Jan', 'kowalski','{bcrypt}$2y$12$R3m9wqwHTyKbhqGYoXgBMuDvNTqxs7aTJnE4XQwdo1LC9h375O5AS',
         'ROLE_ADMIN');

INSERT INTO APP_USER(id, email,first_name, last_name, password, role)
VALUES (100002, 'andrzej.brzozowski@Gmail.com', 'Andrzej', 'Brzozowski','{bcrypt}$2y$12$946HuJr2jwqLLe5wqwJBJeupc.TS/3MdUFXDnqGpbVh8VfW5T6dZW',
        'ROLE_USER');

INSERT INTO APP_USER(id, email,first_name, last_name, password, role)
VALUES (100003, 'janina.kowalczyk@gmail.com', 'Janina', 'Kowalczyk','{bcrypt}$2y$12$946HuJr2jwqLLe5wqwJBJeupc.TS/3MdUFXDnqGpbVh8VfW5T6dZW',
        'ROLE_CREATOR');

