INSERT INTO user (name,  password, enabled)
  values ('Users',
    'password',
    1);

INSERT INTO authorities (name, authority)
  values ('Users', 'USER_ROLE');

 