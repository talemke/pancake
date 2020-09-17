# Installing



## Database

### Migrating database (MySQL)
```mysql
-- Create 'pancake_accounts'
CREATE TABLE `pancake_accounts` (
    `account_id` VARCHAR(63) NOT NULL,
    `name` VARCHAR(63) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `group_id` VARCHAR(63) NOT NULL,
    `flags` BIGINT NOT NULL,
    PRIMARY KEY (`account_id`), INDEX (`group_id`)
);

-- Create 'pancake_groups'
CREATE TABLE `pancake_groups` (
    `group_id` VARCHAR(63) NOT NULL,
    `name` VARCHAR(63) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`group_id`)
);

-- Create 'pancake_inboxes'
CREATE TABLE `pancake_inboxes` (
    `inbox_id` VARCHAR(63) NOT NULL,
    `name` VARCHAR(63) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `account_id` VARCHAR(63) NOT NULL,
    PRIMARY KEY (`inbox_id`), INDEX (`account_id`)
);

-- Create 'pancake_emails'
CREATE TABLE `pancake_emails` (
    `email_id` VARCHAR(63) NOT NULL,
    `timestamp` BIGINT NOT NULL,
    `sender` VARCHAR(255) NOT NULL,
    `recipient` VARCHAR(255) NOT NULL,
    `data` LONGTEXT NOT NULL,
    `headers` LONGTEXT NOT NULL,
    `content` LONGTEXT NOT NULL,
    `helo` VARCHAR(255) NOT NULL,
    `remote_address` VARCHAR(255) NOT NULL,
    `type` TINYINT NOT NULL DEFAULT 0,
    `account_id` VARCHAR(63) NOT NULL,
    `inbox_id` VARCHAR(63) NULL DEFAULT NULL,
    PRIMARY KEY (`email_id`), INDEX (`account_id`), INDEX (`inbox_id`)
);

-- Create 'pancake_routes'
CREATE TABLE `pancake_routes` (
    `route_id` VARCHAR(63) NOT NULL,
    `account_id` VARCHAR(63) NOT NULL,
    `username_string` VARCHAR(255) NOT NULL,
    `username_type` ENUM('EXACT', 'REGEX', 'ANY') NOT NULL,
    `hostname_string` VARCHAR(255) NOT NULL,
    `hostname_type` ENUM('EXACT', 'REGEX', 'ANY') NOT NULL,
    PRIMARY KEY (`route_id`), INDEX (`account_id`)
);
```
