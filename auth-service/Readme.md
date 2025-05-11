Steps:

1. Run docker-compose up -d
2. Verify using docker ps

To View Data from postgres:
1. docker exec -it access-db bash
2. psql -U <USER_NAME> -d <DB_NAME> (psql -U root -d access_control)
3. Run any SQL command (SELECT * FROM users;)