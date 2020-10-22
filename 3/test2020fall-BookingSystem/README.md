# Booking system.

## Requirements:

A running mysql server exposed at port 3307.
<br>Root password must be testuser123

### Setup:

- Clone this repo.
- Install Docker.
- Run this command to set up mysql in a Docker container: \
  <code>docker run -d --rm \
  --name mysql-test-db \
  -e MYSQL_ROOT_PASSWORD=testuser123 \
  -p 3307:3306
  mysql</code>

### Features:

- Booking-, employee- and customer creation have been tested at unit and integration level.
- Invocation of SmsService in accordance with the specified requirements has been tested at unit level.
- If a SmsMessage cannot be sent due to missing or incomplete phone number, the SmsMessage status field is updated accordingly. The value of this field is also tested against.

Enjoy!
