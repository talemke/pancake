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
    `helo` VARCHAR(255) NOT NULL,
    `remote_address` VARCHAR(255) NOT NULL,
    `deleted` TINYINT NOT NULL DEFAULT 0,
    `draft` TINYINT NOT NULL DEFAULT 0,
    `outgoing` TINYINT NOT NULL DEFAULT 0,
    `account_id` VARCHAR(63) NOT NULL,
    `inbox_id` VARCHAR(63) NULL DEFAULT NULL,
    PRIMARY KEY (`email_id`), INDEX (`account_id`), INDEX (`inbox_id`)
);
```
